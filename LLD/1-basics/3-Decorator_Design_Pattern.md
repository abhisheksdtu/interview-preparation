
# Decorator Design Pattern

## Intent
- Attach additional responsibilities (behaviors or features) to an object dynamically without modifying its code.
- Enables flexible composition of behaviors at runtime.

---

## 1. “Before” Decorator (Class Explosion)

Imagine modeling a pizza shop with every combination of toppings using subclasses. For N toppings, you'd need \(2^N\) subclasses.

```java
// Pizza interface
public interface Pizza {
    String getDescription();
    double getCost();
}

// Plain pizza class
public class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }
    @Override
    public double getCost() {
        return 5.00;
    }
}

// Cheese pizza subclass
public class CheesePizza extends PlainPizza {
    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese";
    }
    @Override
    public double getCost() {
        return super.getCost() + 1.50;
    }
}

// Pepperoni pizza subclass
public class PepperoniPizza extends PlainPizza {
    @Override
    public String getDescription() {
        return super.getDescription() + ", Pepperoni";
    }
    @Override
    public double getCost() {
        return super.getCost() + 2.00;
    }
}

// Cheese + Pepperoni pizza subclass
public class CheesePepperoniPizza extends PlainPizza {
    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese, Pepperoni";
    }
    @Override
    public double getCost() {
        return super.getCost() + 1.50 + 2.00;
    }
}

// And so on for each combination...
```

**Problems:**
- With 3 toppings (Cheese, Pepperoni, Olives), you'd need \(2^3 = 8\) subclasses:
  - `CheesePizza`, `PepperoniPizza`, `OlivesPizza`, `CheesePepperoniPizza`, `CheeseOlivesPizza`, etc.
- With 5 toppings, you'd need \(2^5 = 32\) subclasses.
- Unmanageable and violates the Open/Closed Principle.
- The classes aren’t truly “closed” for modification: to support a new topping (or a new combination), you have to touch existing code or create an entirely new subclass.

---

## 2. “After” Decorator Pattern (Flexible Composition)

Instead of subclass explosion, use decorators:

1. **Pizza Interface**  
    ```java
    public interface Pizza {
        String getDescription();
        double getCost();
    }
    ```

2. **PlainPizza (Base Component)**  
    ```java
    public class PlainPizza implements Pizza {
        @Override
        public String getDescription() {
            return "Plain Pizza";
        }
        @Override
        public double getCost() {
            return 5.00;
        }
    }
    ```

3. **Abstract Decorator**  
    ```java
    public abstract class PizzaDecorator implements Pizza {
        protected final Pizza pizza;

        public PizzaDecorator(Pizza pizza) {
            this.pizza = pizza;
        }

        @Override
        public String getDescription() {
            return pizza.getDescription();
        }
        @Override
        public double getCost() {
            return pizza.getCost();
        }
    }
    ```

4. **Concrete Decorators**  
    ```java
    public class CheeseDecorator extends PizzaDecorator {
        public CheeseDecorator(Pizza pizza) {
            super(pizza);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Cheese";
        }
        @Override
        public double getCost() {
            return super.getCost() + 1.50;
        }
    }

    public class PepperoniDecorator extends PizzaDecorator {
        public PepperoniDecorator(Pizza pizza) {
            super(pizza);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Pepperoni";
        }
        @Override
        public double getCost() {
            return super.getCost() + 2.00;
        }
    }

    public class OlivesDecorator extends PizzaDecorator {
        public OlivesDecorator(Pizza pizza) {
            super(pizza);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Olives";
        }
        @Override
        public double getCost() {
            return super.getCost() + 1.00;
        }
    }
    ```

5. **Client Code (Runtime Composition)**  
    ```java
    public class PizzaStore {
        public static void main(String[] args) {
            Pizza myPizza = new PlainPizza(); // Base cost: 5.00

            // Add toppings dynamically
            myPizza = new CheeseDecorator(myPizza);     // Cost: 6.50
            myPizza = new PepperoniDecorator(myPizza);  // Cost: 8.50
            myPizza = new OlivesDecorator(myPizza);     // Cost: 9.50

            System.out.println("Description: " + myPizza.getDescription());
            // Output: Plain Pizza, Cheese, Pepperoni, Olives

            System.out.printf("Total Cost: $%.2f%n", myPizza.getCost());
            // Output: Total Cost: $9.50
        }
    }
    ```

---

## 3. UML Diagram (Decorator Pattern)

```text
                        «interface»
                           Pizza
    +-------------------------------------------+
    | + getDescription(): String               |
    | + getCost(): double                      |
    +-------------------------------------------+
                                 ▲
                                 │
            ┌────────────────────┴────────────────────┐
            │                                         │
    +----------------------+               +------------------------------+
    |     PlainPizza       |               |       PizzaDecorator         |
    +----------------------+               +------------------------------+
    | + getDescription()   |               | – pizza: Pizza               |
    | + getCost()          |               +------------------------------+
    |                      |               | + PizzaDecorator(pizza: Pizza)|
    +----------------------+               | + getDescription(): String   |
                                           | + getCost(): double           |
                                           +------------------------------+
                                                 ▲
                                                 │
         ┌──────────────────────┬────────────────┼───────────────────────┐
         │                      │                │                       │
+----------------------+  +----------------------+  +----------------------+
|  CheeseDecorator     |  |  PepperoniDecorator  |  |  OlivesDecorator     |
+----------------------+  +----------------------+  +----------------------+
| + getDescription()   |  | + getDescription()   |  | + getDescription()   |
| + getCost()          |  | + getCost()          |  | + getCost()          |
+----------------------+  +----------------------+  +----------------------+

```

---

## 4. Class Explosion vs. Decorator

- **Without Decorator**: Subclass explosion requires \(2^N\) subclasses for N toppings.
- **With Decorator**: Only 1 base class + N decorators = \(N+1\) classes. Flexible composition at runtime.

---

## 5. When to Use Decorator Pattern

- **Adding Responsibilities Dynamically**: Attach features (toppings, logging, security) to objects at runtime.
- **Avoiding Subclass Explosion**: When subclassing leads to too many combinations.
- **Adhering to Open/Closed Principle**: Introduce new decorators without modifying existing code.
- **Mix-and-Match Behaviors**: Combine multiple responsibilities in arbitrary order.
- **Transparent to Clients**: Decorated objects conform to the same interface (Pizza).

---

## 6. Summary Checklist

- **“Before”**: Many subclasses or large conditional logic to support every combination; hard to maintain.
- **“After”**: 
  1. Create a `Pizza` interface.
  2. Implement a base class `PlainPizza`.
  3. Create an abstract decorator `PizzaDecorator` that wraps a `Pizza`.
  4. Implement concrete decorators (e.g., `CheeseDecorator`, `PepperoniDecorator`, `OlivesDecorator`).
  5. Compose decorators over the base pizza at runtime.

Use Decorator whenever you need flexible, runtime composition of features without subclass explosion.
