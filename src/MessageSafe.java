import java.util.ArrayList;
import java.util.Scanner;

// Non-public helper class
class Message {
    public String text;           
    public int viewsRemaining;    
    public int shift;             

    public Message(String text, int views, int shift) {
        this.text = text;
        this.viewsRemaining = views;
        this.shift = shift;
    }

    public String toString() {
        return "Message(views remaining: " + viewsRemaining + ")";
    }
}

// Public class (matches filename)
public class MessageSafe {
    private ArrayList<Message> messageList;

    public MessageSafe() {
        messageList = new ArrayList<>();
    }

    public void addMessage(String messageText, int numberOfViews, int shiftAmount) {
        String encrypted = encryptText(messageText, shiftAmount);
        messageList.add(new Message(encrypted, numberOfViews, shiftAmount));
        System.out.println("Message added successfully!");
    }

    private String encryptText(String text, int shiftAmount) {
        if (text == null) return null;
        StringBuilder result = new StringBuilder();
        for (char charecter : text.toCharArray()) {
            if (Character.isLetter(charecter)) {
                char base = Character.isUpperCase(charecter) ? 'A' : 'a';
                char shifted = (char) ((charecter - base + shiftAmount) % 26 + base);
                result.append(shifted);
            } else {
                result.append(charecter);
            }
        }
        return result.toString();
    }

    private String decryptText(String text, int shiftAmount) {
        if (text == null) return null;
        shiftAmount = shiftAmount % 26;
        StringBuilder result = new StringBuilder();
        for (char charecter : text.toCharArray()) {
            if (Character.isLetter(charecter)) {
                char base = Character.isUpperCase(charecter) ? 'A' : 'a';
                char shifted = (char) ((charecter - base - shiftAmount + 26) % 26 + base);
                result.append(shifted);
            } else {
                result.append(charecter);
            }
        }
        return result.toString();
    }

    private boolean hasMessageExpired(Message message) {
        return message.viewsRemaining <= 0;
    }
    
    public String viewMessage(int messageIndex) {
        if (messageIndex < 0 || messageIndex >= messageList.size()) {
            return "Invalid message index";
        }
        Message selectedMessage = messageList.get(messageIndex);
        if (hasMessageExpired(selectedMessage)) {
            return "Message expired";
        }
        selectedMessage.viewsRemaining--;
        return decryptText(selectedMessage.text, selectedMessage.shift);
    }

    public void listAllMessages() {
        if (messageList.isEmpty()) {
            System.out.println("No messages in the safe.");
            return;
        }
        System.out.println("Messages in safe:");
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            System.out.println(i + ": " + message.text + " (Views remaining: " + message.viewsRemaining + ")");
        }
    }

    public static void main(String[] args) {
        MessageSafe myMessageSafe = new MessageSafe();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MessageSafe!");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - Add a message\n2 - View a message\n3 - List all messages\n4 - Exit");
            System.out.print("Enter choice: ");
            String userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                System.out.print("Enter message text: ");
                String messageText = scanner.nextLine();
                System.out.print("Enter number of views allowed: ");
                int numberOfViews = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter shift amount for encryption: ");
                int shiftAmount = Integer.parseInt(scanner.nextLine());
                myMessageSafe.addMessage(messageText, numberOfViews, shiftAmount);
            } else if (userInput.equals("2")) {
                System.out.print("Enter message index to view: ");
                int messageIndex = Integer.parseInt(scanner.nextLine());
                System.out.println("Message: " + myMessageSafe.viewMessage(messageIndex));
            } else if (userInput.equals("3")) {
                myMessageSafe.listAllMessages();
            } else if (userInput.equals("4")) {
                System.out.println("Exiting MessageSafe. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}