package com.example.Eventy.Utils;

import com.example.Eventy.Dto.Event.EventDTO;
import com.example.Eventy.Dto.User.UserDTO; // Assurez-vous que UserDTO est importé
import com.example.Eventy.Service.EventService;
import com.example.Eventy.Service.UserService; // Assurez-vous d'importer UserService
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataGenerator { //implements CommandLineRunner { décommenter si on veut éxecuter au démarrage

    private final EventService eventService;
    private final UserService userService; // Ajout de UserService
    private final Faker faker = new Faker(new Random());
    private final Random random = new Random();

    private static final List<String> LOCATIONS = List.of(
            "Louvre Museum", "Eiffel Tower", "Montmartre", "Champs-Elysées", "Seine River",
            "Le Marais", "Latin Quarter", "Versailles Palace", "Disneyland Paris"
    );

    private static final List<String> EVENT_TYPES = List.of("Concert", "Exhibition", "Conference", "Festival", "Workshop");

   /* @Override décommenter si on veut utilsier au début
    public void run(String... args) throws Exception {
        generateUsers(10); // Générez 10 utilisateurs
        generateEvents(10); // Générez 10 événements
    }*/

    public void generateEvents(int count) {
        List<EventDTO> events = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String type = EVENT_TYPES.get(random.nextInt(EVENT_TYPES.size()));
            String title = generateEventTitle(type);
            String location = LOCATIONS.get(random.nextInt(LOCATIONS.size()));
            String description = generateEventDescription(type);

            LocalDateTime startDate = generateRandomStartDate();
            LocalDateTime endDate = startDate.plusHours(1 + random.nextInt(5)); // Duration: 1-5 hours

            EventDTO eventDTO = new EventDTO();
            eventDTO.setTitle(title);
            eventDTO.setDescription(description);
            eventDTO.setStartDate(startDate);
            eventDTO.setEndDate(endDate);
            eventDTO.setLocation(location);

            events.add(eventDTO);
        }

        events.forEach(eventService::createEvent);
    }

    // Méthode pour générer des utilisateurs aléatoires
    public void generateUsers(int count) {
        List<UserDTO> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(faker.internet().emailAddress());
            userDTO.setPassword(faker.internet().password()); // Vous pouvez appliquer des règles de complexité si nécessaire

            users.add(userDTO);
        }

        users.forEach(userService::createUser); // Enregistrez les utilisateurs dans la base de données
    }

    private String generateEventTitle(String type) {
        String artistOrTopic = faker.book().title();
        return switch (type) {
            case "Concert" -> "Concert: " + artistOrTopic;
            case "Exhibition" -> "Exposition: " + artistOrTopic;
            case "Conference" -> "Conférence: " + artistOrTopic;
            case "Festival" -> "Festival: " + artistOrTopic;
            case "Workshop" -> "Atelier: " + artistOrTopic;
            default -> "Événement: " + artistOrTopic;
        };
    }

    private String generateEventDescription(String type) {
        return switch (type) {
            case "Concert" -> "Concert de " + faker.music().genre() + " avec " + faker.artist().name();
            case "Exhibition" -> "Exposition sur le thème de " + faker.book().title();
            case "Conference" -> "Conférence sur " + faker.company().industry();
            case "Festival" -> "Festival célébrant " + faker.music().genre();
            case "Workshop" -> "Atelier pratique de " + faker.job().field();
            default -> "Événement autour de " + faker.lorem().sentence();
        };
    }

    private LocalDateTime generateRandomStartDate() {
        int daysInFuture = random.nextInt(30);
        int hour = 10 + random.nextInt(12); // From 10 AM to 10 PM
        return LocalDateTime.now(ZoneId.of("Europe/Paris")).plusDays(daysInFuture).withHour(hour).withMinute(0);
    }
}
