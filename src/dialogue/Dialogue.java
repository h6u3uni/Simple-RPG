/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogue;

/**
 *
 * @author haruk
 */
public class Dialogue {
    /*
     * Dialogue is a class that contains all the dialogue for the game.
     * It is a static class, so it can be accessed without creating an instance of it.
     * Dialogue is stored in arrays of strings.
     */

    // Dialogue for the intro
    public static String[] getDialogueIntro(){
        return Story.getStoryIntro();
    }
    
    // Dialogue for the act 1
    public static String[] getDialogueOne(){
        String[] array1 = Elf.getAct1ElfDialogue();
        String[] array2 = Story.getActOneIntro();

        String[] combinedArray = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
    
    // Dialogue for the act 2
    public static String[] getDialogueTwo(){            
        String[] array1 = Story.getActOneOutro();
        String[] array2 = Elf.getAct2ElfDialogue();
        String[] array3 = Story.getActTwoIntro();

        int combinedLength = array1.length + array2.length + array3.length;
        String[] combinedArray = new String[combinedLength];

        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);
        System.arraycopy(array3, 0, combinedArray, array1.length + array2.length, array3.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
    
    // Dialogue for the act 3
    public static String[] getDialogueThree(){
        String[] array1 = Story.getActTwoOutro();
        String[] array2 = Elf.getAct3ElfDialogue();
        String[] array3 = Story.getActThreeIntro();

        int combinedLength = array1.length + array2.length + array3.length;
        String[] combinedArray = new String[combinedLength];

        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);
        System.arraycopy(array3, 0, combinedArray, array1.length + array2.length, array3.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
    
    // Dialogue for the act 4
    public static String[] getDialogueFour(){
        String[] array1 = Story.getActThreeOutro();
        String[] array2 = Elf.getAct4ElfDialogue();
        String[] array3 = Story.getActFourIntro();

        int combinedLength = array1.length + array2.length + array3.length;
        String[] combinedArray = new String[combinedLength];

        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);
        System.arraycopy(array3, 0, combinedArray, array1.length + array2.length, array3.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
    
    // Dialogue for the act 5
    public static String[] getDialogueFive(){
        String[] array1 = Story.getActFourOutro();
        String[] array2 = Elf.getAct5ElfDialogue();
        String[] array3 = Story.getActFiveIntro();

        int combinedLength = array1.length + array2.length + array3.length;
        String[] combinedArray = new String[combinedLength];

        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);
        System.arraycopy(array3, 0, combinedArray, array1.length + array2.length, array3.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
    
    // Dialogue for the act 6
    public static String[] getDialogueSix(){
        String[] array1 = Story.getActFiveOutro();
        String[] array2 = Story.getStoryOutro();

        String[] combinedArray = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);

        // Print the combined array
//        for (String element : combinedArray) {
//            System.out.println(element);
//        }
        
        return combinedArray;
    }
}
