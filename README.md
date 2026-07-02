# MessageSafe
Code for the AP Exam
--------------------


📦 MessageSafe

MessageSafe is a Java console application that lets users store encrypted messages with limited views. Each message is protected using a Caesar cipher and becomes unavailable after being viewed a set number of times.

🚀 Features
🔐 Encrypts messages using a Caesar cipher (shift-based encryption)
🔓 Automatically decrypts messages when viewed
👁️ Limits how many times each message can be viewed
📋 Lists all stored messages with remaining views
🧠 Simple menu-driven console interface
🧱 How It Works

Each message is stored as a Message object containing:

text → encrypted message
viewsRemaining → number of times it can still be viewed
shift → encryption shift used for the Caesar cipher

When a message is viewed:

The program checks if it has expired
It decreases the remaining view count
It decrypts and displays the original message
🔐 Encryption System

MessageSafe uses a Caesar Cipher:

Each letter is shifted by a fixed number
Uppercase and lowercase letters are preserved
Non-letter characters are not changed
Decryption reverses the shift

Example:

Shift = 3  
HELLO → KHOOR
🎮 Program Features (Console Menu)

The program provides a simple menu system:

Add a message
View a message
List all messages
Exit
📁 File Structure
MessageSafe.java   → Main program + Message class
⚠️ Notes
Messages are stored only in memory (not saved permanently)
Once the program exits, all messages are lost
Message indexes are used to access stored messages
💡 Possible Improvements
Save messages to a file for persistence
Add password protection
Allow deleting or editing messages
Upgrade to a graphical interface (JavaFX or Swing)

If you want, I can also make it look more like a real GitHub repo README (with emojis, banners, and cleaner “project card” style) or shorten it to something your teacher would prefer 👍
