class Node {
    private String data;
    private Node next;

    public Node() {
        this("~", null);
    }

    public Node(String d) {
        data = d;
    }

    public Node(String d, Node n) {
        data = d;
        next = n;
    }

    public void setData(String newData) {
        data = newData;
    }

    public void setNext(Node newNext) {
        next = newNext;
    }

    public String getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void displayNode() {
        System.out.print(data);
    }
}

class Queue {
    private int size;
    private Node front;
    private Node rear;

    public Queue() {
        size = 0;
        front = null;
        rear = null;
    }

    public boolean empty() {
        return size == 0;
    }

    public void enqueue(String token) {
        Node newRear = new Node();
        newRear.setData(token);
        newRear.setNext(null);
        if (this.empty())
            front = newRear;
        else
            rear.setNext(newRear);
        rear = newRear;
        size++;
    }

    public String dequeue() {
        String i = front.getData();
        front = front.getNext();
        size--;
        if (this.empty())
            rear = null;
        return i;
    }

    public String front() {
        return front.getData();
    }

    public int size() {
        return size;
    }
}

class Stack {
    private Node top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public boolean empty() {
        return top == null;
    }

    public void push(String token) {
        Node newTop = new Node();
        newTop.setData(token);
        newTop.setNext(top);
        top = newTop;
        size++;
    }

    public String pop() {
        String i = top.getData();
        top = top.getNext();
        size--;
        return i;
    }

    public String ontop() {
        return top.getData();
    }

    public int size() {
        return size;
    }
}

public class Arithmetic {
    static Queue expToInfixQueue(String expression) {
        Queue infixQueue = new Queue();
        String number = "";
        for (int i = 0; i < expression.length(); i++) {
            String token = expression.substring(i, i + 1);
            if (token.equals(" ") && !number.isEmpty()) {
                infixQueue.enqueue(number);
                number = "";
            } else if ("+-*/()".contains(token)) {
                if (!number.isEmpty()) {
                    infixQueue.enqueue(number);
                    number = "";
                }
                infixQueue.enqueue(token);
            } else if (i == expression.length() - 1) {
                number += token;
                infixQueue.enqueue(number);
                number = "";
            } else if (!token.equals(" ")) {
                number += token;
            }
        }
        infixQueue.enqueue("#");
        return infixQueue;
    }

    static Queue infixToPostfix(Queue infixQueue) {
        Queue postfixQueue = new Queue();
        Stack operatorStack = new Stack();
        operatorStack.push("#"); // initialize operator stack
        while (!infixQueue.empty()) {
            String token = infixQueue.dequeue(); // dequeue next token
            if (!"*/+-#()".contains(token)) { // token is operand
                postfixQueue.enqueue(token); // enqueue on postfix
            } else if (token.equals("#")) { // token is #
                while (!operatorStack.empty()) {
                    postfixQueue.enqueue(operatorStack.pop()); // pop all & enqueue
                }
            } else if (token.equals(")")) { // token right parenthesis
                String stackToken = operatorStack.pop(); // pop entries & enqueue until left parenthesis
                while (!stackToken.equals("(") && !stackToken.equals("#")) {
                    postfixQueue.enqueue(stackToken);
                    stackToken = operatorStack.pop();
                }
            } else {
                String stackToken = operatorStack.pop();
                while (stackPriority(stackToken) >= infixPriority(token) && !stackToken.equals("(")) {
                    postfixQueue.enqueue(stackToken);
                    stackToken = operatorStack.pop();
                }
                operatorStack.push(stackToken);
                operatorStack.push(token);
            }
        }
        return postfixQueue;
    }

    static int infixPriority(String token) {
        return switch (token) {
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            case "(" -> 3;
            default -> 0;
        };
    }

    static int stackPriority(String token) {
        return switch (token) {
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            case "(" -> 3;
            default -> 0;
        };
    }

    static float postfixEval(Queue postfixQueue) {
        Stack valueStack = new Stack();
        while (!postfixQueue.empty()) {
            String token = postfixQueue.dequeue();
            if (!"*/+-#()".contains(token)) {
                valueStack.push(token);
            } else if (!token.equals("#")) {
                float num2 = Float.parseFloat(valueStack.pop());
                float num1 = Float.parseFloat(valueStack.pop());
                switch (token) {
                    case "*" -> {
                        num1 *= num2;
                        valueStack.push(String.valueOf(num1));
                    }
                    case "/" -> {
                        num1 /= num2;
                        valueStack.push(String.valueOf(num1));
                    }
                    case "+" -> {
                        num1 += num2;
                        valueStack.push(String.valueOf(num1));
                    }
                    case "-" -> {
                        num1 -= num2;
                        valueStack.push(String.valueOf(num1));
                    }
                }
            }
        }
        return Float.parseFloat(valueStack.pop());
    }

    static String queueToString(Queue q) {
        Queue newQueue = new Queue();
        String queueString = "";
        while (!q.empty()) {
            String token = q.dequeue();
            if (!token.equals("#"))
                queueString += token + " ";
            newQueue.enqueue(token);
        }
        while (!newQueue.empty()) {
            String token = newQueue.dequeue();
            q.enqueue(token);
        }
        return queueString;
    }

    static String stackToString(Stack s) {
        Stack newStack = new Stack();
        String stackString = "";
        while (!s.empty()) {
            String token = s.pop();
            stackString += token + " ";
            newStack.push(token);
        }
        while (!newStack.empty()) {
            String token = newStack.pop();
            s.push(token);
        }
        return stackString;
    }

    public static void main(String[] args) {
        System.out.println("Infix Expression: ((A * B) / C) + D");
        String expression1 = "((A * B) / C) + D";
        Queue exp1InfQ = expToInfixQueue(expression1);
        System.out.println("Infix Queue: " + queueToString(exp1InfQ));
        Queue exp1PostQ = infixToPostfix(exp1InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp1PostQ) + "\n");

        System.out.println("Infix Expression: A * B + (C - D / E)");
        String expression2 = "A * B + (C - D / E)";
        Queue exp2InfQ = expToInfixQueue(expression2);
        System.out.println("Infix Queue: " + queueToString(exp2InfQ));
        Queue exp2PostQ = infixToPostfix(exp2InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp2PostQ) + "\n");

        System.out.println("Infix Expression: 5 * 3 + (6 - 8 / 2)");
        String expression3 = "5 * 3 + (6 - 8 / 2)";
        Queue exp3InfQ = expToInfixQueue(expression3);
        System.out.println("Infix Queue: " + queueToString(exp3InfQ));
        Queue exp3PostQ = infixToPostfix(exp3InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp3PostQ));
        System.out.println("Postfix Evaluation: " + postfixEval(exp3PostQ) + "\n");

        System.out.println("Infix Expression: (2+9*9)-9*(8-6+0)-6+2*(6/3)");
        String expression4 = "(2+9*9)-9*(8-6+0)-6+2*(6/3)";
        Queue exp4InfQ = expToInfixQueue(expression4);
        System.out.println("Infix Queue: " + queueToString(exp4InfQ));
        Queue exp4PostQ = infixToPostfix(exp4InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp4PostQ));
        System.out.println("Postfix Evaluation: " + postfixEval(exp4PostQ) + "\n");

        System.out.println("Infix Expression: (8*2)+6*(8/1)-9+7*0+5*4");
        String expression5 = "(8*2)+6*(8/1)-9+7*0+5*4";
        Queue exp5InfQ = expToInfixQueue(expression5);
        System.out.println("Infix Queue: " + queueToString(exp5InfQ));
        Queue exp5PostQ = infixToPostfix(exp5InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp5PostQ));
        System.out.println("Postfix Evaluation: " + postfixEval(exp5PostQ) + "\n");

        System.out.println("Infix Expression: 99/11+2*1-5+50/100");
        String expression6 = "99/11+2*1-5+50/100";
        Queue exp6InfQ = expToInfixQueue(expression6);
        System.out.println("Infix Queue: " + queueToString(exp6InfQ));
        Queue exp6PostQ = infixToPostfix(exp6InfQ);
        System.out.println("Postfix Queue: " + queueToString(exp6PostQ));
        System.out.println("Postfix Evaluation: " + postfixEval(exp6PostQ));
    }
}
