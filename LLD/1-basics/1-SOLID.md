- **S - Single Responsibility Principle**
- **O - Open / Closed Principle**
- **L - Liskov Substitution Principle**
- **I - Interface Segregation Principle**
- **D - Dependency Inversion Principle**

---

## Advantages of SOLID
- Promotes **clean, maintainable, and extensible code**
- Reduces duplication and coupling
- Makes it easier to understand, test, and refactor each piece in isolation

---

## Single Responsibility Principle (SRP)
> **“A class should have only one reason to change.”**

**Bad Example (Multiple Responsibilities):**
```java
// This class both manages users, sends emails, and generates reports.
// If any email logic or report logic changes, you'd need to modify this same class.
class UserManager {
    public void createUser() { /* ... */ }
    public void sendEmail() { /* ... */ }
    public void generateReport() { /* ... */ }
}
```

**Good Example (Each class has a single responsibility):**
```java
// Now, each class has exactly one reason to change.
// If email logic changes, only EmailService changes; UserManager is untouched.
class UserManager {
    public void createUser() { /* ... */ }
}

class EmailService {
    public void sendEmail() { /* ... */ }
}

class ReportGenerator {
    public void generateReport() { /* ... */ }
}
```

---

## Open / Closed Principle (OCP)
> **“Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.”**

In other words, you should be able to add new behavior without altering existing, tested code.

**Bad Example (Not following OCP):**
```java
class Rectangle {
    public double width;
    public double height;
}

class AreaCalculator {
    public double calculateArea(Rectangle rectangle) {
        // If we later add Circles, Triangles, etc., we'd have to modify this method:
        return rectangle.width * rectangle.height;
    }
}
```

**Good Example (Following OCP):**
```java
// Define a Shape interface that any new shape can implement.
// We never modify AreaCalculator; we only add new Shape implementations.
interface Shape {
    double calculateArea();
}

class Rectangle implements Shape {
    public double width;
    public double height;

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle implements Shape {
    public double radius;

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}
```

---

## Liskov Substitution Principle (LSP)
> **“If class B is a subtype of class A, then anywhere we use an instance of A, we should be able to substitute an instance of B without changing the desirable properties of the program.”**
>
> In practice: a subclass should **extend** the parent’s behavior, never **narrow** or break it.

### 1. Common (but misleading) Bird Example

#### Bad Example (Violating LSP)
```java
// Here, Bird declares a fly() method that all birds are expected to perform.
// But Penguin cannot fly—so overriding fly() to throw an exception breaks LSP.
abstract class Bird {
    public abstract void fly();
    public void eat() { /* ... */ }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        // Sparrow can fly
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        // Throws a runtime exception → cannot substitute Penguin wherever Bird is used.
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```

- **Why this violates LSP:**
  - Anywhere client code expects a `Bird` and calls `fly()`, substituting a `Penguin` will cause a runtime failure.
  - The contract of `Bird` promised “all birds fly,” but `Penguin` narrowed that contract.

#### Good Example (Following LSP)
```java
// Extract the behaviors into separate interfaces.
// Now each “bird” only implements what it can safely do.
interface Bird {
    void eat();
}

interface FlyingBird extends Bird {
    void fly();
}

interface SwimmingBird extends Bird {
    void swim();
}

class Sparrow implements FlyingBird {
    @Override
    public void fly() {
        // Sparrow’s flying logic
    }

    @Override
    public void eat() {
        // Sparrow’s eating logic
    }
}

class Penguin implements SwimmingBird {
    @Override
    public void swim() {
        // Penguin’s swimming logic
    }

    @Override
    public void eat() {
        // Penguin’s eating logic
    }
}
```

- **Why this follows LSP:**
  1. Any code that expects a `FlyingBird` can safely substitute a `Sparrow` (it has `fly()`).
  2. Any code that expects a `SwimmingBird` can safely substitute a `Penguin` (it has `swim()`).
  3. No subclass breaks the parent interface’s contract (we didn’t force `Penguin` to implement `fly()`).

### 2. Rectangle / Square Example (Another Classic LSP Pitfall)

#### Bad Example (Violating LSP)
```java
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        // A square sets both width and height to the same value.
        this.width = w;
        this.height = w;
    }

    @Override
    public void setHeight(int h) {
        this.width = h;
        this.height = h;
    }
}

// Client code expects any Rectangle to behave “normally”:
public class AreaCalculator {
    // This method assumes that setting width and height independently is valid.
    public void printArea(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);
        // We expect area = 20.
        System.out.println("Area = " + r.getArea());
    }
}

public class Demo {
    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();

        Rectangle rect = new Rectangle();
        calc.printArea(rect); 
        // → Area = 20  (OK)

        Rectangle sq = new Square();
        calc.printArea(sq);
        // → Area = 16  (since Square’s setWidth(5) and setHeight(4) each force width=height)
        // This breaks the client’s expectation that Rectangle#setWidth and #setHeight work independently.
    }
}
```

- **Why it violates LSP:**
  - `Square` is a subtype of `Rectangle`, but it cannot behave exactly like a rectangle when clients assume `width` and `height` are independent.
  - Substituting a `Square` in place of a `Rectangle` leads to incorrect area calculation—thus “breaking” LSP.

#### Good Example (Following LSP)
```java
// Option 1: Don’t let Square inherit from Rectangle. Instead, model both via a common interface.
interface Quadrilateral {
    int getArea();
}

// Rectangle implements Quadrilateral.
class Rectangle implements Quadrilateral {
    protected int width;
    protected int height;

    public Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

// Square also implements Quadrilateral, but does NOT extend Rectangle.
class Square implements Quadrilateral {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int s) {
        this.side = s;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}

// Now, any code that works with a Quadrilateral and only calls getArea()
// can safely use Rectangle or Square interchangeably:
public class AreaPrinter {
    public void printArea(Quadrilateral q) {
        System.out.println("Area = " + q.getArea());
    }
}
```

- **Why this follows LSP:**
  - We removed “forced inheritance” between `Square` and `Rectangle`.
  - Both share a `Quadrilateral` interface that only requires `getArea()`, so neither class can violate the expected behavior of the other.

---

## Interface Segregation Principle (ISP)
> **“Clients should not be forced to depend on methods they do not use.”**

Split large “fat” interfaces into smaller, more specific ones so that classes only implement what they actually need.

**Bad Example (Not following ISP):**
```java
// Worker interface demands work(), eat(), and sleep()… 
// but Robot doesn’t eat or sleep.
interface Worker {
    void work();
    void eat();
    void sleep();
}

class Robot implements Worker {
    @Override
    public void work() { /* ... */ }

    @Override
    public void eat() {
        // Robots don’t eat → forced to throw an exception.
        throw new UnsupportedOperationException();
    }

    @Override
    public void sleep() {
        // Robots don’t sleep → forced to throw an exception.
        throw new UnsupportedOperationException();
    }
}
```

**Good Example (Following ISP):**
```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

class Human implements Workable, Eatable, Sleepable {
    @Override
    public void work() { /* ... */ }
    @Override
    public void eat() { /* ... */ }
    @Override
    public void sleep() { /* ... */ }
}

// Robot only needs to implement Workable.
class Robot implements Workable {
    @Override
    public void work() { /* ... */ }
}
```

---

## Dependency Inversion Principle (DIP)
> **“High-level modules should not depend on low-level modules; both should depend on abstractions. Abstractions should not depend on details; details should depend on abstractions.”**

In practice, this means your classes depend on interfaces (or abstract classes), not concrete implementations.

**Bad Example (Not following DIP):**
```java
class MySQLDatabase {
    public void save(String data) { /* ... */ }
}

class UserService {
    private MySQLDatabase database;

    public UserService() {
        // Directly instantiating a concrete class → hard to swap out DB implementations.
        this.database = new MySQLDatabase();
    }

    public void saveUser(String userData) {
        database.save(userData);
    }
}
```

- **Why this violates DIP:**
  - `UserService` is tightly coupled to `MySQLDatabase`.
  - To use PostgreSQL (or a mock), you’d have to modify `UserService` itself.

**Good Example (Following DIP):**
```java
// Define an abstraction for saving data.
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    @Override
    public void save(String data) { /* MySQL-specific save logic */ }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) { /* Postgres-specific save logic */ }
}

// UserService depends on the Database abstraction, not a concrete class.
class UserService {
    private Database database;

    // Inject whichever implementation is desired.
    public UserService(Database database) {
        this.database = database;
    }

    public void saveUser(String userData) {
        database.save(userData);
    }
}

// Client code can now choose at runtime which DB to use:
Database mysql = new MySQLDatabase();
UserService service1 = new UserService(mysql);

Database postgres = new PostgreSQLDatabase();
UserService service2 = new UserService(postgres);
```