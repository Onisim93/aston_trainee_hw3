package org.example.aston_trainee_hw3;

import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.AttractionType;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.model.ServiceEntity;

import java.time.LocalDate;
import java.util.List;

public class MockTestEntityData {

    private MockTestEntityData() {}

    public static final LocalityEntity firstLocality = LocalityEntity.builder()
            .id(1l)
            .name("Самара")
            .hasMetro(true)
            .population(2_000_000)
            .build();

    public static final LocalityEntity secondLocality = LocalityEntity.builder()
            .id(2l)
            .name("Санкт-петербург")
            .hasMetro(true)
            .population(5_600_000)
            .build();


    public static final LocalityEntity newLocality = LocalityEntity.builder()
            .name("Москва")
            .hasMetro(true)
            .population(11_500_000)
            .build();

    public static final LocalityEntity newLocalityCreated = LocalityEntity.builder()
            .id(3l)
            .name("Москва")
            .hasMetro(true)
            .population(11_500_000)
            .build();

    public static final LocalityEntity updatedLocality = LocalityEntity.builder()
            .id(1l)
            .name("Самара")
            .hasMetro(false)
            .population(1_200_000)
            .build();


    public static final ServiceEntity firstService = ServiceEntity.builder()
            .id(1l)
            .name("Свободное посещение.")
            .description("Свободное посещение без гида. До закрытия.")
            .build();

    public static final ServiceEntity secondService = ServiceEntity.builder()
            .id(2l)
            .name("Индивидуальная экскурсия с гидом.")
            .description("Индвидуальная экскурсия только для Вас, с профессиональным гидом. Время экскурсии - 2 часа.")
            .build();

    public static final ServiceEntity thirdService = ServiceEntity.builder()
            .id(3l)
            .name("Групповая экскурсия с гидом.")
            .description("Групповая экскурсия с профессиональным гидом. Время экскурсии - 3 часа.")
            .build();

    public static final ServiceEntity newService = ServiceEntity.builder()
            .name("Абонемент на посещение аттракционов.")
            .description("Дает право на проход на любые аттракционы в пределах парка, в течение указанного времени. Время действие - до закрытия.")
            .build();

    public static final ServiceEntity newServiceCreated = ServiceEntity.builder()
            .id(4l)
            .name("Абонемент на посещение аттракционов.")
            .description("Дает право на проход на любые аттракционы в пределах парка, в течение указанного времени. Время действие - до закрытия.")
            .build();

    public static final ServiceEntity updatedService = ServiceEntity.builder()
            .id(1l)
            .name("(Обновленное) Свободное посещение.")
            .description("(Обновленное) Свободное посещение без гида. До закрытия.")
            .build();


    public static final AttractionEntity firstAttraction = AttractionEntity.builder()
            .id(1l)
            .name("Национальный парк Самарская лука")
            .creationDate(LocalDate.parse("1984-04-28"))
            .description("Самарская Лука — один из уникальных уголков России. Топоним «Самарская Лука» включает в себя несколько понятий: излучина Волги у Жигулёвских гор; восточный участок Приволжской возвышенности; полуостровная территория, окружённая одноимённой излучиной Волги и Усинским заливом Куйбышевского водохранилища.")
            .type(AttractionType.RESERVE)
            .serviceEntities(List.of(firstService, secondService, thirdService))
            .locality(firstLocality)
            .build();

    public static final AttractionEntity secondAttraction = AttractionEntity.builder()
            .id(2l)
            .name("Музей авиации и космонавтики")
            .creationDate(LocalDate.parse("1977-01-12"))
            .description("Музей авиации и космонавтики — это место, которое обязательно стоит посетить всем, кто интересуется космосом и историей авиации. В музее представлены личные вещи и фотографии Сергея Королева, а также экспонаты из разных эпох, связанных с развитием воздухоплавания и покорением космоса.")
            .type(AttractionType.MUSEUM)
            .serviceEntities(List.of(firstService, secondService, thirdService))
            .locality(firstLocality)
            .build();

    public static final AttractionEntity thirdAttraction = AttractionEntity.builder()
            .id(3l)
            .name("Екатерининский дворец")
            .creationDate(LocalDate.parse("1717-01-01"))
            .description("Большо́й Екатери́нинский дворе́ц (Большо́й Царскосе́льский дворе́ц, Екатери́нинский дворе́ц, Большо́й дворе́ц[1], Стáрый дворе́ц[2]) — императорский дворец, официальная летняя резиденция трёх российских правительниц: Екатерины I, Елизаветы Петровны, Екатерины II. Дворец был построен в 26 км к югу от центра Санкт-Петербурга в его пригороде Царское Село (ныне в составе Пушкинского района Санкт-Петербурга).")
            .type(AttractionType.PALACE)
            .locality(secondLocality)
            .serviceEntities(List.of(firstService, secondService, thirdService))
            .build();

    public static final AttractionEntity fourthAttraction = AttractionEntity.builder()
            .id(4l)
            .name("Эрмитаж")
            .creationDate(LocalDate.parse("1764-03-01"))
            .description("Эрмита́ж (от фр. ermitage — место уединения, келья, приют отшельника, затворничество), Госуда́рственный Эрмита́ж (сокр. ГЭ; до 1917 года — Императорский Эрмитаж) — российский государственный художественный и культурно-исторический музей в Санкт-Петербурге, одно из крупнейших в мире учреждений подобного рода.")
            .type(AttractionType.MUSEUM)
            .locality(secondLocality)
            .serviceEntities(List.of(firstService, secondService, thirdService))
            .build();

    public static final AttractionEntity newAttraction = AttractionEntity.builder()
            .name("Парк Гагарина")
            .description("Парк в центре города, с катком, катамаранами и колесом обозрения.")
            .creationDate(LocalDate.parse("1997-06-01"))
            .type(AttractionType.PARK)
            .locality(firstLocality)
            .serviceEntities(List.of(firstService))
            .build();

    public static final AttractionEntity newAttractionCreated = AttractionEntity.builder()
            .id(5l)
            .name("Парк Гагарина")
            .description("Парк в центре города, с катком, катамаранами и колесом обозрения.")
            .creationDate(LocalDate.parse("1997-06-01"))
            .type(AttractionType.PARK)
            .locality(firstLocality)
            .serviceEntities(List.of(firstService))
            .build();

    public static final AttractionEntity updatedAttraction = AttractionEntity.builder()
            .id(1l)
            .name("Национальный парк Самарская лука")
            .creationDate(LocalDate.parse("1984-04-28"))
            .description("(Обновленное описание) Самарская Лука — один из уникальных уголков России. Топоним «Самарская Лука» включает в себя несколько понятий: излучина Волги у Жигулёвских гор; восточный участок Приволжской возвышенности; полуостровная территория, окружённая одноимённой излучиной Волги и Усинским заливом Куйбышевского водохранилища.")
            .type(AttractionType.RESERVE)
            .serviceEntities(List.of(firstService, secondService, thirdService))
            .locality(firstLocality)
            .build();


    public static final List<LocalityEntity> allLocalities = List.of(firstLocality, secondLocality);
    public static final List<AttractionEntity> allAttractions = List.of(firstAttraction, secondAttraction, thirdAttraction, fourthAttraction);
    public static final List<ServiceEntity> allServices = List.of(firstService, secondService, thirdService);

    public static final List<AttractionEntity> museumAttractionsForSamara = List.of(secondAttraction);
    public static final List<AttractionEntity> samaraAttractions = List.of(firstAttraction, secondAttraction);
}
