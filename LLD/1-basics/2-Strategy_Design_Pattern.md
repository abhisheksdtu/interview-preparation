# Strategy Design Pattern

## 1. Intent
- Encapsulate interchangeable algorithms behind a common interface.
- Let clients choose (or swap) a concrete strategy at runtime.

---

## 2. Problem Addressed
- Avoid a single class filled with `if/else` (or `switch`) selecting behaviors.
- Decouple “which algorithm” from “how to run it.”

---

## 3. Participants
1. **Strategy (interface)**  
    `honk()` (or `execute()`, `calculate()`, etc.)  
2. **ConcreteStrategy A, B, C**  
    Implement `Strategy` with different behavior.  
3. **Context**  
    Holds a `Strategy` reference, delegates calls, and can change it via `setStrategy(...)`.  
4. **Client**  
    Creates/injects the chosen `ConcreteStrategy` into the `Context`.  

### 3.1. UML “Before” (Association Only)

```text
───────────────────────────────────────
|           Order                     |
|─────────────────────────────────────|
| – orderId: String                   |
| – amount: double                    |
|─────────────────────────────────────|
| + getOrderId(): String              |
| + getAmount(): double               |
───────────────────────────────────────

      1
CheckoutService ———─ uses ———─► Order

───────────────────────────────────────────────
|           CheckoutService                  |
|─────────────────────────────────────────────|
| + pay(order: Order, paymentType: String): void |
───────────────────────────────────────────────
```

---

## 4. “Before” Strategy Pattern (All Logic in One Class)

```java
// Order class
public class Order {
    private String orderId;
    private double amount;

    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
    public String getOrderId() { return orderId; }
    public double getAmount()   { return amount;   }
}

// CheckoutService with hardcoded payment logic
public class CheckoutService {
    public void pay(Order order, String paymentType) {
        if (paymentType.equals("CREDIT_CARD")) {
            System.out.println("Processing credit card payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            // … call credit-card API …
        }
        else if (paymentType.equals("PAYPAL")) {
            System.out.println("Processing PayPal payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            // … call PayPal SDK …
        }
        else if (paymentType.equals("BITCOIN")) {
            System.out.println("Processing Bitcoin payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            // … call Bitcoin wallet API …
        }
        else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
        System.out.println("Payment completed.\n");
    }
}
```

**Drawbacks:**
- Every new payment method (e.g., “APPLEPAY”) requires editing `CheckoutService.pay(...)`.
- Long `if–else` chains become hard to maintain.
- Violates Open/Closed Principle.

---

## 5. “After” Strategy Pattern (Separate Strategy Classes)

1. **Strategy Interface**
    ```java
    public interface PaymentStrategy {
        void pay(Order order);
    }
    ```

2. **Concrete Strategies**
    ```java
    public class CreditCardStrategy implements PaymentStrategy {
        @Override
        public void pay(Order order) {
            System.out.println("Processing credit card payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            System.out.println("Credit card payment completed.\n");
        }
    }

    public class PayPalStrategy implements PaymentStrategy {
        @Override
        public void pay(Order order) {
            System.out.println("Processing PayPal payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            System.out.println("PayPal payment completed.\n");
        }
    }

    public class BitcoinStrategy implements PaymentStrategy {
        @Override
        public void pay(Order order) {
            System.out.println("Processing Bitcoin payment for order " 
                + order.getOrderId() + " of amount $" + order.getAmount());
            System.out.println("Bitcoin payment completed.\n");
        }
    }
    ```

3. **Context (CheckoutService) Uses a Strategy**
    ```java
    public class CheckoutService {
        private PaymentStrategy strategy;

        public CheckoutService(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        public void pay(Order order) {
            strategy.pay(order);
        }
    }
    ```

4. **Client Code**
    ```java
    public class MainAfter {
        public static void main(String[] args) {
            Order order = new Order("A123", 100.00);

            CheckoutService service = new CheckoutService(new CreditCardStrategy());
            service.pay(order);  // Credit card flow

            service.setPaymentStrategy(new PayPalStrategy());
            service.pay(order);  // PayPal flow

            service.setPaymentStrategy(new BitcoinStrategy());
            service.pay(order);  // Bitcoin flow
        }
    }
    ```

---

### 5.1. UML “After” (Is-a & Has-a Relationships)

```text
                      <<interface>>
                    PaymentStrategy
                    + pay(order: Order): void
                           ▲
                           │ «realizes» (is-a)
    ┌──────────────────────┴───────────────────────┐
    │                      │                       │
┌─────────────────┐  ┌─────────────────┐   ┌─────────────────┐
│CreditCardStrategy│ │PayPalStrategy    │   │BitcoinStrategy  │
│─────────────────│ │─────────────────│   │─────────────────│
│+ pay(order): void│ │+ pay(order): void│   │+ pay(order): void│
└─────────────────┘ └─────────────────┘   └─────────────────┘


    CheckoutService «has-a» PaymentStrategy
            ◇
            │
 ┌────────────────────────────────────────────────┐
 │               CheckoutService                 │
 │────────────────────────────────────────────────│
 │– strategy: PaymentStrategy                    │
 │────────────────────────────────────────────────│
 │+ CheckoutService(strategy: PaymentStrategy)   │
 │+ setPaymentStrategy(strategy: PaymentStrategy): void │
 │+ pay(order: Order): void                      │
 └────────────────────────────────────────────────┘
            │
            │ «uses»
            ▼
 ┌───────────────────────────────────────────┐
 │                 Order                    │
 │───────────────────────────────────────────│
 │– orderId: String                         │
 │– amount: double                          │
 │───────────────────────────────────────────│
 │+ getOrderId(): String                    │
 │+ getAmount(): double                     │
 └───────────────────────────────────────────┘
```

---

## 6. When to Use
- You have multiple ways to perform one task that should be interchangeable.
- You want to avoid large conditional logic deciding which algorithm to run.
- You need to swap behaviors at runtime without modifying client code.
- You want each algorithm in its own class for easier testing and extension.
