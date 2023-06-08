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

// class to save static methods just to print the story text
public class Story {
    
//    public static void printStoryIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Story");
//        System.out.println("In a quiet village nestled deep in the forest, you lived a simple life as a young warrior. ");
//        System.out.println("You hunted and trained to hone your skills in combat. One fateful night, however, changed ");
//        System.out.println("everything. As you returned from a successful hunt, you saw the village in flames, the air ");
//        System.out.println("thick with smoke and the ground littered with bodies. The demon lord had attacked, burning ");
//        System.out.println("the village to the ground and killing everyone in it, including your family.");
//        System.out.println("");
//        System.out.println("Filled with rage and the desire for revenge, you set out on a journey to track down and ");
//        System.out.println("confront the demon lord. You knew that you couldn't take on the demon lord alone, so you ");
//        System.out.println("decided to seek out the help of the elves, who were known for their long lives and vast ");
//        System.out.println("knowledge. After traveling for days through treacherous landscapes and battling dangerous ");
//        System.out.println("creatures, you arrived at the elven city, where you were met with skepticism and distrust.");
//        Logic.gamePauser();
//    }
    protected static String[] getStoryIntro(){
        String[] out = new String[2];
        String p1 = Logic.createHeading("Story") + "\n";
        p1 += "In a quiet village nestled deep in the forest, you lived a simple life as a young warrior. \n";
        p1 += "You hunted and trained to hone your skills in combat. One fateful night, however, changed \n";
        p1 += "everything. As you returned from a successful hunt, you saw the village in flames, the air \n";
        p1 += "thick with smoke and the ground littered with bodies. The demon lord had attacked, burning \n";
        p1 += "the village to the ground and killing everyone in it, including your family.\n\nPress continue...\n";
        String p2 = Logic.createHeading("Story") + "\n";
        p2 += "Filled with rage and the desire for revenge, you set out on a journey to track down and \n";
        p2 += "confront the demon lord. You knew that you couldn't take on the demon lord alone, so you \n";
        p2 += "decided to seek out the help of the elves, who were known for their long lives and vast \n";
        p2 += "knowledge. After traveling for days through treacherous landscapes and battling dangerous \n";
        p2 += "creatures, you arrived at the elven city, where you were met with skepticism and distrust.\n\nPress continue...\n";
        out[0] = p1;
        out[1] = p2;
        
        return out;
    }
    
    
//    public static void printActOneIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 1: Elf's Approval");
//        System.out.println("The elves had seen many adventurers come and go, each one seeking their help to defeat the ");
//        System.out.println("demon lord, but all of them had failed. To prove your worth, the elves gave you a series of ");
//        System.out.println("quests to complete. The first task is to clear the \"Goblin Forest\" and help the elves ");
//        System.out.println("recover peace in their forest. ");
//        Logic.gamePauser();
//    }
    
    protected static String[] getActOneIntro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 1: Elf's Approval") + "\n";
        p1 += "The elves had seen many adventurers come and go, each one seeking their help to defeat the  \n";
        p1 += "demon lord, but all of them had failed. To prove your worth, the elves gave you a series of \n";
        p1 += "quests to complete. The first task is to clear the \"Goblin Forest\" and help the elves  \n";
        p1 += "recover peace in their forest. \n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
    
//    public static void printActOneOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 1: Elf's Approval - Complete");
//        System.out.println("The goblins had been causing chaos and destruction, and their king was a formidable opponent. ");
//        System.out.println("Despite the odds, you defeated the goblin king and gained the \"Elf's approval.\"");
//        System.out.println("With the \"Elf's Approval\" in your possession, you were one step closer to defeating the demon lord.");
//        Logic.gamePauser();
//    }
    protected static String[] getActOneOutro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 1: Elf's Approval - Complete") + "\n";
        p1 += "The goblins had been causing chaos and destruction, and their king was a formidable opponent. \n";
        p1 += "Despite the odds, you defeated the goblin king and gained the \"Elf's approval.\"\n";
        p1 += "With the \"Elf's Approval\" in your possession, you were one step closer to defeating the demon lord.\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActTwoIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 2: Trial of Courage");
//        System.out.println("The elves have instructed you to complete the trial of courage at the \"Ancient Tomb,\" where ");
//        System.out.println("you must face an army of undeads and a powerful Dark Knight protecting the tomb. ");
//        Logic.gamePauser();
//    }
    protected static String[] getActTwoIntro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 2: Trial of Courage") + "\n";
        p1 += "The elves have instructed you to complete the trial of courage at the \"Ancient Tomb,\" where  \n";
        p1 += "you must face an army of undeads and a powerful Dark Knight protecting the tomb. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActTwoOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 2: Trial of Courage - Complete");
//        System.out.println("With sheer determination and unwavering bravery, you emerged victorious and gained the \"Proof of Courage.\" ");
//        System.out.println("With the \"Proof of Courage\" in your possession, you felt more confident and prepared to face the demon lord.");
//        Logic.gamePauser();
//    }
    protected static String[] getActTwoOutro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 2: Trial of Courage - Complete") + "\n";
        p1 += "With sheer determination and unwavering bravery, you emerged victorious and gained the \"Proof of Courage.\"  \n";
        p1 += "With the \"Proof of Courage\" in your possession, you felt more confident and prepared to face the demon lord. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActThreeIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 3: Trial of Wisdom");
//        System.out.println("Next, the elves tasked you with completing the trial of wisdom at the \"Minotaur's Labyrinth.\" ");
//        System.out.println("You must use your intellect to navigate through the maze-like structure and face the Minotaur, ");
//        System.out.println("who was known for his immense strength. ");
//        Logic.gamePauser();
//    }
    protected static String[] getActThreeIntro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 3: Trial of Wisdom") + "\n";
        p1 += "Next, the elves tasked you with completing the trial of wisdom at the \"Minotaur's Labyrinth.\"  \n";
        p1 += "You must use your intellect to navigate through the maze-like structure and face the Minotaur, \n";
        p1 += "who was known for his immense strength. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActThreeOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 3: Trial of Wisdom - Complete");
//        System.out.println("Through careful planning and strategy, you defeated the Minotaur and gained the \"Proof of Wisdom.\"");
//        System.out.println("With the \"Proof of Wisdom\" in your possession, you felt smarter and more prepared to face the demon lord.");
//        Logic.gamePauser();
//    }
    protected static String[] getActThreeOutro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 3: Trial of Wisdom - Complete") + "\n";
        p1 += "Through careful planning and strategy, you defeated the Minotaur and gained the \"Proof of Wisdom.\" \n";
        p1 += "With the \"Proof of Wisdom\" in your possession, you felt smarter and more prepared to face the demon lord.";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActFourIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 4: Trial of Power");
//        System.out.println("Finally, the elves sent you on the last trial, the trial of power at the \"Death Mountain,\" where ");
//        System.out.println("you must face an ancient dragon. ");
//        Logic.gamePauser();
//    }
    protected static String[] getActFourIntro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 4: Trial of Power") + "\n";
        p1 += "Finally, the elves sent you on the last trial, the trial of power at the \"Death Mountain,\" where \n";
        p1 += "you must face an ancient dragon. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActFourOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 4: Trial of Power - Complete");
//        System.out.println("The dragon was the most challenging opponent yet, but you emerged victorious and gained the \"Proof of Power.\"");
//        System.out.println("With the \"Proof of Power\" in your possession, you felt stronger and more ready than ever to face the demon lord.");
//        Logic.gamePauser();
//    }
    protected static String[] getActFourOutro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 4: Trial of Power - Complete") + "\n";
        p1 += "The dragon was the most challenging opponent yet, but you emerged victorious and gained the \"Proof of Power.\" \n";
        p1 += "With the \"Proof of Power\" in your possession, you felt stronger and more ready than ever to face the demon lord. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActFiveIntro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 5: Confronting the Demon Lord");
//        System.out.println("With all three proofs in your possession, you were deemed worthy by the elves and were given the map ");
//        System.out.println("to the Demon Castle. The final battle was about to begin, and you were ready to take on the demon ");
//        System.out.println("lord and avenge your village. ");
//        Logic.gamePauser();
//    }
    protected static String[] getActFiveIntro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 5: Confronting the Demon Lord") + "\n";
        p1 += "With all three proofs in your possession, you were deemed worthy by the elves and were given the map  \n";
        p1 += "to the Demon Castle. The final battle was about to begin, and you were ready to take on the demon \n ";
        p1 += "lord and avenge your village. ";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printActFiveOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Act 5: Confronting the Demon Lord - Complete");
//        System.out.println("In the end, you emerged victorious, and the demon lord was defeated.");
//        Logic.gamePauser();
//    }
    protected static String[] getActFiveOutro(){
        String[] out = new String[1];
        String p1 = Logic.createHeading("Act 5: Confronting the Demon Lord - Complete") + "\n";
        p1 += "In the end, you emerged victorious, and the demon lord was defeated.";
        p1 += "\n\nPress continue...\n";
        out[0] = p1;
        return out;
    }
//    public static void printStoryOutro(){
//        Logic.clearConsole();
//        Logic.printHeading("Story - Complete");
//        System.out.println("As you emerged from the castle, you felt a sense of closure and relief. You had avenged your village and ");
//        System.out.println("the memory of your family. You knew that you could finally rest easy, knowing that justice had been served.");
//        Logic.gamePauser();
//    }
    protected static String[] getStoryOutro(){
        String[] out = new String[3];
        String p1 = Logic.createHeading("Story - Complete") + "\n";
        p1 += "As you emerged from the castle, you felt a sense of closure and relief. You had avenged your village and \n";
        p1 += "the memory of your family. You knew that you could finally rest easy, knowing that justice had been served.";
        p1 += "\n\nPress continue...\n";
        String p2 = Logic.createHeading("CONGRATULATIONS!") + "\n";
        p2 += "That concludes the main story of my very simple, Simple RPG. Thank you very much for playing. ";
        p2 += "\n\nPress continue...\n";
        String p3 = Logic.createHeading("???") + "\n";
        p3 += "Only the bravest of braves shall find the lone god.";
        p3 += "\n\nPress continue...\n";
        out[0] = p1;
        out[1] = p2;
        out[2] = p3;
        return out;
    }
}
