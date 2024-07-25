# Spring Boot Rest Application

## Описание

Это Spring Boot REST веб-приложение, использующее PostgreSQL в качестве базы данных. Для тестирования используется TestContainers. Развертывание приложения доступно в Docker containers.

## Настройка базы данных

Инициализация базы данных проходит автоматически при запуске. Включая тестовые данные.

### Локальная разработка с TestContainers

TestContainers автоматически настроит и запустит контейнер с PostgreSQL для интеграционных тестов. Никакой дополнительной настройки не требуется. Необходим только работающий docker на локальной машине.

### Инструкция по запуску

Подтянуть проект в локальный репозиторий и в папке проекта, в консоли ввести команду docker-compose up --build. На локальной машине должен быть запущен Docker.

## Resources

### Locality

- Endpoint: /api/locality
    - GET: Retrieve all localities
    - POST: Create a new locality
        - Fields:
            - name: String (required)
            - population: Integer (required)
            - hasMetro: Boolean (required)

- Endpoint: /api/locality/{id}
    - GET: Retrieve locality by ID
    - PUT: Update locality by ID
        - Fields:
            - id: Long (required)
            - population: Integer (required)
            - hasMetro: Boolean (required)
    - DELETE: Delete locality by ID

### Attraction

- Endpoint: /api/attraction
    - GET: Retrieve all attractions (optional filters: sort_by, type, locality_name, withRec)
    - POST: Create a new attraction
        - Fields:
            - name: String (required)
            - creationDate: String (required, format = dd-MM-yyyy)
            - description: String (required)
            - type: String (required, values=[PALACE, PARK, MUSEUM, ARCHAEOLOGICAL_SITE, RESERVE])
            - localityId: Integer (required, existing locality ID)
            - serviceIds: List<Integer> (required, at least 1 existing service ID)

- Endpoint: /api/attraction/{id}
    - GET: Retrieve attraction by ID
    - PUT: Update attraction by ID
        - Fields:
            - id: Long (required)
            - description: String (optional)
    - DELETE: Delete attraction by ID

### Service

- Endpoint: /api/service
    - GET: Retrieve all services
    - POST: Create a new service
        - Fields:
            - name: String (required)
            - description: String (required)

- Endpoint: /api/service/{id}
    - GET: Retrieve service by ID
    - DELETE: Delete service by ID