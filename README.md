# FoodFlow — What I Learned

## 1. Spring Core
- `@Primary` vs `@Qualifier`.
- Singleton vs Prototype bean scopes.
- Injecting prototype beans into singleton beans using `ObjectProvider`.
- `@PostConstruct` lifecycle callback.

## 2. JPA & Hibernate
- Owning vs inverse sides of entity relationships.
- `mappedBy` and `@JoinColumn`.
- Why `OrderItem` needs a separate entity instead of `@ManyToMany`.
- Storing historical prices using `purchasePrice`.
- Custom JPQL queries and derived query methods.
- Pessimistic locking using `@Lock` and `PESSIMISTIC_WRITE`.

## 3. Transaction Management
- Transaction rollback using `@Transactional`.
- Atomic order placement and inventory management.
- Restoring stock during order cancellation.
- Handling duplicate menu items using `LinkedHashMap`.

## 4. Business Logic & Java
- Strategy pattern using multiple discount implementations.
- Percentage vs flat discount calculations with `BigDecimal`.
- Order status transitions using enums and switch expressions.
- Generating order numbers using UUID.
- Understanding idempotency in order submissions.

## 5. Validation & Exception Handling
- Bean Validation vs database constraints.
- Centralized exception handling using `@RestControllerAdvice`.
- Handling validation, type mismatch and missing parameter exceptions.
- Designing a consistent API error response.

## 6. Advanced Spring Data
- Pagination and sorting using `Page`, `Pageable` and `@PageableDefault`.
- Transforming paginated results using `Page.map()`.

---

**Key takeaway:** Gained practical experience with entity relationship design, bean scopes, transaction management, concurrency control, business rule enforcement and advanced Spring Data JPA concepts.