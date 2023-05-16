/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogue;

import rpggame.Logic;

/**
 *
 * @author haruk
 */

//class that has static methods just for printing dialogue for the elf npc.
public class Elf {
    public static void printAct1ElfDialogue(){
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Hold on, young warrior. What brings you to our city?\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"I seek your aid in my quest for revenge against the demon lord. \nHe destroyed my village and killed my family.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"I see. But why should we trust you? What have you done to prove yourself?\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"I have trained for years as a warrior and I am determined to seek \njustice for my family. I'm willing to do whatever it takes to prove my worthiness.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Impressive, but one victory does not prove your worthiness. However, "
                        + "\nif you truly seek our help, there is a way you can prove yourself. The goblin "
                        + "\nforest has been a problem for us for some time. If you can clear it of the goblin \ninfestation, we may consider helping you.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"I understand. I'll do whatever it takes.\"");
        Logic.gamePauser();
    }
    
    public static void printAct2ElfDialogue(){
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"I see that you have proven yourself in battle. But to face the demon lord, you \n"
                         + "will need more than just strength. You will need courage. And there's no better place \n"
                         + "to test your courage than the ancient tomb. Many brave warriors have entered, but none \n"
                         + "have returned. Are you willing to take on this challenge?\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"Yes, I am. I will face whatever dangers lie ahead.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Good. Be careful in there. The tomb is full of traps and monsters. If you survive, \nyou will have proved your courage.\"");
        Logic.gamePauser();
    }
    
    public static void printAct3ElfDialogue(){
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Now that you have proven your courage, it's time to test your wisdom. The minotaur \n"
                         + "labyrinth is a place of great mystery and danger. It is said that the maze contains a secret \n"
                         + "room with a powerful artifact. But to find it, you will need to solve riddles and puzzles. \n"
                         + "Can you handle this challenge?\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"I'm ready. I will use my wit and intelligence to overcome any obstacle.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Good luck. The maze is treacherous and confusing. Only the wisest of warriors have \nbeen able to make it through.\"");
        Logic.gamePauser();
    }
    
    public static void printAct4ElfDialogue(){
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"You have proven yourself worthy in both courage and wisdom. But to face the demon lord, \n"
                         + "you will need raw power. And there's no better test of your power than Death Mountain. It is a \n"
                         + "place of great heat and danger, where the elements themselves will try to destroy you. Are you \n"
                         + "ready for this challenge?\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Player: \"Yes, I am. I will face Death Mountain and come out victorious.\"");
        Logic.gamePauser();
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"Very well. The mountain is filled with lava and fire. Only the strongest of warriors can \n"
                         + "survive its trials. But if you can make it through, you will have proven your power.\"");
        Logic.gamePauser();
    }
    
    public static void printAct5ElfDialogue(){
        Logic.clearConsole();
        Logic.printHeading("Elven City");
        System.out.println("Elf: \"You have truly proven yourself to be a worthy warrior. You have passed every trial and have \n"
                         + "earned our respect and trust. Here is the map to the demon castle. It will lead you straight to the \n"
                         + "demon lord. Be warned, he is not to be underestimated. He is a powerful foe, and defeating him will \n"
                         + "not be easy. But with your skills and our support, we believe you can do it. May the gods be with you.\"");
        Logic.gamePauser();
    }
    
}
