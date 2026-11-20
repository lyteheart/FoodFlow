# FoodFlow — What I Learned

## 1. Spring Core & Dependency Injection

- Constructor-based dependency injection.
- Interface-based service design.
- `@Primary` and `@Qualifier` for resolving multiple implementations of an interface.
- Singleton and prototype bean scopes.
- Injecting prototype beans into singleton beans using `ObjectProvider`.
- `@PostConstruct` for executing initialization logic after bean creation.

## 2. Spring Data JPA & Hibernate

- Entity relationships using `@OneToMany` and `@ManyToOne`.
- Understanding owning and inverse sides of relationships.
- Using `mappedBy` and `@JoinColumn`.
- Repository interfaces with `JpaRepository`.
- Derived query methods such as `findByRestaurantId()`.
- Custom JPQL queries using `@Query`.
- Repository locking with `@Lock` and `LockModeType.PESSIMISTIC_WRITE`.
- Database constraints and foreign key relationships.
- Mapping Java enums to database values using `@Enumerated(EnumType.STRING)`.

## 3. Entity Relationships & Data Modelling

- Designing relationships between Restaurant, MenuItem, Order and OrderItem.
- Using a separate `OrderItem` entity instead of a direct `@ManyToMany` relationship.
- Understanding why relationships with additional attributes require an intermediate entity.
- Storing `purchasePrice` as a historical price snapshot.
- Enforcing the rule that one order can contain items from only one restaurant.

## 4. DTOs & Bean Validation

- Separating API request/response models from JPA entities.
- Creating request and response DTOs.
- Mapping entities to DTOs and DTOs to entities.
- Using `@Valid` for request validation.
- Using `@NotNull`, `@NotBlank`, `@NotEmpty`, `@Positive`, `@Min`, `@DecimalMin`, `@Digits`, `@Email` and `@Pattern`.
- Understanding the difference between Bean Validation constraints and database constraints.

## 5. Service Layer & Business Logic

- Separating business logic from controller and repository layers.
- Implementing CRUD operations through service interfaces and implementations.
- Using `BigDecimal` for monetary calculations.
- Implementing percentage-based and flat discount strategies.
- Using `BigDecimal.min()` to prevent discounts from exceeding the order amount.
- Generating unique order references using UUID.
- Understanding how `Stream`, `map()` and method references simplify DTO conversion.

## 6. Transaction Management & Inventory

- Using `@Transactional` for atomic business operations.
- Understanding transaction rollback when order placement fails.
- Validating stock availability before completing an order.
- Updating inventory when an order is placed.
- Restoring inventory when an order is cancelled.
- Understanding how pessimistic locking protects records during concurrent updates.

## 7. Order Management & Business Rules

- Implementing an order status lifecycle using Java enums.
- Validating allowed status transitions using switch expressions.
- Implementing order cancellation rules.
- Preventing cancellation of completed or otherwise ineligible orders.
- Aggregating duplicate menu item IDs using `LinkedHashMap`.
- Understanding why repeated order submissions can create separate orders and the concept of idempotency.

## 8. Exception Handling

- Creating custom runtime exceptions:
  - `NotFoundException`
  - `InvalidInputException`
  - `ElementAlreadyExistException`
- Centralized exception handling using `@RestControllerAdvice`.
- Handling exceptions using `@ExceptionHandler`.
- Handling validation failures using `MethodArgumentNotValidException`.
- Handling invalid request parameters using `MethodArgumentTypeMismatchException`.
- Handling missing request parameters using `MissingServletRequestParameterException`.
- Creating a consistent API error response using `ApiErrorResponse`.
- Returning appropriate HTTP status codes through `ResponseEntity`.

## 9. REST API Development

- Building REST controllers using `@RestController` and `@RequestMapping`.
- Implementing GET, POST, PUT, PATCH and DELETE endpoints.
- Using `@PathVariable`, `@RequestParam` and `@RequestBody`.
- Using `ResponseEntity` to control HTTP responses.
- Understanding HTTP status codes such as 200, 201, 204, 400, 404 and 409.
- Testing APIs using Postman.

## 10. Pagination & Sorting

- Using Spring Data's `Page` and `Pageable`.
- Implementing pagination through `findAll(Pageable)`.
- Converting paginated entity results into DTOs using `Page.map()`.
- Configuring default pagination and sorting using `@PageableDefault`.
- Understanding zero-based page indexing.
---

**Key takeaway:** Built practical experience with Spring Boot, Spring Data JPA, Hibernate, REST APIs, dependency injection, transaction management, validation, exception handling, business logic and database relationships through the development of FoodFlow.