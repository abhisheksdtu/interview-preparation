
# Factory & Abstract Factory Design Patterns (Concise Notes)

---

## Factory Design Pattern

### Intent
- Define an interface for creating a single type of object, and let subclasses or factory methods decide which concrete class to instantiate.
- Decouple client code from concrete implementations.

### 1. “Before” Factory Pattern (Direct Instantiation)

```java
// PaymentProcessor interface
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Concrete implementations
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// Client code creating processors directly
public class PaymentService {
    public void makePayment(String type, double amount) {
        PaymentProcessor processor;
        if (type.equals("CREDIT_CARD")) {
            processor = new CreditCardProcessor();
        } else if (type.equals("PAYPAL")) {
            processor = new PayPalProcessor();
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
        processor.processPayment(amount);
    }
}

// Usage
public class MainBeforeFactory {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.makePayment("CREDIT_CARD", 100.00);
        service.makePayment("PAYPAL", 50.00);
    }
}
```

**Drawbacks (“Before”)**
- **Tight Coupling**: `PaymentService` knows each concrete `PaymentProcessor`.
- **Long Conditional Logic**: Adding a new type requires modifying `makePayment`.
- **Violates Open/Closed Principle**: Must change existing code to add new processor.

---

### 2. “After” Factory Pattern

#### 2.1. Factory Class

```java
public class PaymentProcessorFactory {
    public static PaymentProcessor createProcessor(String type) {
        switch (type) {
            case "CREDIT_CARD":
                return new CreditCardProcessor();
            case "PAYPAL":
                return new PayPalProcessor();
            // Add more types here (e.g., "BITCOIN")
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
```

#### 2.2. Client Code Uses the Factory

```java
public class PaymentService {
    public void makePayment(String type, double amount) {
        PaymentProcessor processor = PaymentProcessorFactory.createProcessor(type);
        processor.processPayment(amount);
    }
}

// Usage
public class MainAfterFactory {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.makePayment("CREDIT_CARD", 100.00);
        service.makePayment("PAYPAL", 50.00);
    }
}
```

**Improvements (“After”)**
- **Single Responsibility**: `PaymentService` no longer handles instantiation logic.
- **Centralized Creation**: All creation logic is in `PaymentProcessorFactory`.
- **Easier Extension**: To add `BitcoinProcessor`, implement `PaymentProcessor` and update `PaymentProcessorFactory`.

---

### 3. UML Diagram (Factory Pattern)

```text
                     <<interface>>
                   PaymentProcessor
                   + processPayment(amount: double): void
                          ▲ «implements»
                          │
          ┌───────────────┴───────────────┐
          │                               │
┌─────────────────┐             ┌──────────────────┐
│CreditCardProcessor│             │PayPalProcessor   │
│─────────────────│             │──────────────────│
│+ processPayment(..)│             │+ processPayment(..)│
└─────────────────┘             └──────────────────┘

                          ▲ uses ▲
                            │   │
            ┌──────────────────────────────────────────┐
            │        PaymentProcessorFactory           │
            │──────────────────────────────────────────│
            │+ createProcessor(type: String): PaymentProcessor │
            └──────────────────────────────────────────┘
                            ▲ uses ▲
                              │   │
            ┌──────────────────────────────────────────┐
            │               PaymentService             │
            │──────────────────────────────────────────│
            │+ makePayment(type: String, amount: double): void │
            └──────────────────────────────────────────┘
```

- **is-a («implements»)**: `CreditCardProcessor` and `PayPalProcessor` are `PaymentProcessor`.
- **uses**: `PaymentService` uses `PaymentProcessorFactory`, and factory uses `PaymentProcessor` interface.

---

### 4. When to Use Factory Pattern
- **Multiple Related Classes**: Many concrete implementations of a single product interface.
- **Decouple Client**: Hide concrete class names from client code.
- **Centralize Creation Logic**: Manage caching, pooling, configuration decisions in one place.
- **Variability**: Choose the concrete class based on input, configuration, or environment.

---

## Abstract Factory Design Pattern

### Intent
- Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
- Ensure products from a given family are compatible.

### 1. “Before” Abstract Factory Pattern (Direct Instantiation)

```java
// Product interfaces
public interface Button {
    void render();
}
public interface Checkbox {
    void render();
}

// Light theme implementations
public class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering light-themed button");
    }
}
public class LightCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering light-themed checkbox");
    }
}

// Dark theme implementations
public class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering dark-themed button");
    }
}
public class DarkCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering dark-themed checkbox");
    }
}

// Client code chooses products directly
public class GUIApplication {
    private Button button;
    private Checkbox checkbox;

    public void createUI(String theme) {
        if (theme.equals("LIGHT")) {
            button = new LightButton();
            checkbox = new LightCheckbox();
        } else if (theme.equals("DARK")) {
            button = new DarkButton();
            checkbox = new DarkCheckbox();
        } else {
            throw new IllegalArgumentException("Unknown theme: " + theme);
        }
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}

// Usage
public class MainBeforeAbstractFactory {
    public static void main(String[] args) {
        GUIApplication app = new GUIApplication();
        app.createUI("LIGHT");
        app.render();
    }
}
```

**Drawbacks (“Before”)**
- **Tight Coupling**: `GUIApplication` knows each `LightButton`, `DarkCheckbox`, etc.
- **Inconsistent Families**: Possible to mix mismatched products (LightButton + DarkCheckbox).

---

### 2. “After” Abstract Factory Pattern

#### 2.1. Abstract Factory Interface

```java
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

#### 2.2. Concrete Factories

```java
// Light theme factory
public class LightThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

// Dark theme factory
public class DarkThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}
```

#### 2.3. Product Interfaces & Implementations

```java
public interface Button {
    void render();
}
public interface Checkbox {
    void render();
}

public class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering light-themed button");
    }
}
public class LightCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering light-themed checkbox");
    }
}

public class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering dark-themed button");
    }
}
public class DarkCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering dark-themed checkbox");
    }
}
```

#### 2.4. Client Code Uses Abstract Factory

```java
public class GUIApplication {
    private final Button button;
    private final Checkbox checkbox;

    public GUIApplication(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}

// Usage
public class MainAfterAbstractFactory {
    public static void main(String[] args) {
        GUIFactory factory = new LightThemeFactory();
        GUIApplication app = new GUIApplication(factory);
        app.render();  // Renders light theme components

        GUIFactory darkFactory = new DarkThemeFactory();
        GUIApplication darkApp = new GUIApplication(darkFactory);
        darkApp.render();  // Renders dark theme components
    }
}
```

---

### 3. UML Diagram (Abstract Factory Pattern)

```text
                                <<interface>>
                                  GUIFactory
                            + createButton(): Button
                           + createCheckbox(): Checkbox
                                       ▲ «implements»
                 ┌─────────────────────┴─────────────────────┐
                 │                                           │
   ┌───────────────────────────┐               ┌─────────────────────────┐
   │      LightThemeFactory    │               │      DarkThemeFactory   │
   │───────────────────────────│               │─────────────────────────│
   │+ createButton(): Button   │               │+ createButton(): Button │
   │+ createCheckbox(): Checkbox│              │+ createCheckbox(): Checkbox│
   └───────────────────────────┘               └─────────────────────────┘

                   ▲ uses ▲                           ▲ uses ▲
                     │   │                             │   │
   ┌──────────────────────────────────┐   ┌──────────────────────────────────┐
   │          LightButton             │   │          DarkButton               │
   │ <<implements>> Button            │   │ <<implements>> Button             │
   └──────────────────────────────────┘   └──────────────────────────────────┘

   ┌──────────────────────────────────┐   ┌──────────────────────────────────┐
   │         LightCheckbox            │   │         DarkCheckbox             │
   │ <<implements>> Checkbox          │   │ <<implements>> Checkbox           │
   └──────────────────────────────────┘   └──────────────────────────────────┘

                                        ▲  
                                        │  has-a  
                                        │  
                              ┌───────────────────────────┐
                              │       GUIApplication      │
                              │───────────────────────────│
                              │– button: Button           │
                              │– checkbox: Checkbox       │
                              │───────────────────────────│
                              │+ GUIApplication(factory: GUIFactory) │
                              │+ render(): void           │
                              └───────────────────────────┘
```

- **is-a («implements»)**: Concrete factories (`LightThemeFactory`, `DarkThemeFactory`) implement `GUIFactory`. Products (`LightButton`, `DarkCheckbox`, etc.) implement their respective interfaces.
- **has-a**: `GUIApplication` holds references to `Button` and `Checkbox`.
- **uses**: Factories use product classes, and `GUIApplication` uses a factory to obtain products.

---

## Differences: Factory vs. Abstract Factory

| Aspect                           | Factory Pattern                                         | Abstract Factory Pattern                                           |
| -------------------------------- | ------------------------------------------------------- | ------------------------------------------------------------------ |
| **Purpose**                      | Create a **single product**                             | Create **families of related products**                            |
| **Number of Methods**            | Typically one method (e.g., `createProcessor(...)`)     | Multiple methods (`createButton()`, `createCheckbox()`)            |
| **Client Coupling**              | Client calls `Factory.create(...)` for one product      | Client calls multiple `Factory.createX()` methods for a family     |
| **Variability**                  | Varies one dimension (e.g., payment method)             | Varies multiple dimensions (e.g., theme for all widgets)           |
| **Use Case**                     | Decouple creation for vertical variability              | Decouple creation for horizontal families of products              |
| **Number of Concrete Factories** | Usually one per product family (static method or class) | One per product family (LightThemeFactory, DarkThemeFactory, etc.) |
| **Ease of Extension**            | Add new `case` or subclass + update factory             | Add new factory class + product classes; client remains unchanged  |
| **Examples**                     | `PaymentProcessorFactory`                               | `GUIFactory` with `LightThemeFactory`, `DarkThemeFactory`          |

---

### When to Use

- **Factory Pattern**: One product interface + many concrete implementations; centralized object creation.
- **Abstract Factory Pattern**: Multiple related product interfaces; need consistent families of products.

