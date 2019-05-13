package springdata;

import entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private final PersonService personService;
    private final EmailService emailService;

    @Scheduled(cron = "${cron}")
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<Person> list = personService.findAllByBirthDay(month, day);
        list.forEach(person -> {
            try {
                String message = "Happy Birthday dear " + person.getName() + "!";
                emailService.send(person.getEmail(), "Happy Birthday!", message);
                log.info("Email have been sent. User id: {}, Date: {}", person.getId(), date);
            } catch (Exception e) {
                log.error("Email can't be sent.User's id: {}, Error: {}", person.getId(), e.getMessage());
                log.error("Email can't be sent", e);
            }
        });
    }
}
