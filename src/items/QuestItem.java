/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import rpggame.Logic;

/**
 *
 * @author haruk
 */
public class QuestItem extends Item{
    public String description;
    
    public QuestItem(String name, String description) {
        super(name, false);
        this.description = description;
    }

    @Override
    public void printItem() {
        Logic.clearConsole();
        Logic.printHeading(name);
        System.out.println(description);
    }
    
    // converts string to quest object. used for saving .
    public static QuestItem parseQuest(String str) {
        String[] parts = str.split(":");
        String name = parts[0];
        String description = parts[1];
        return new QuestItem(name, description);
    }

    //checks if the questitem is same as this questitem. used for act control.
    // This static method parses a string in the format "name:description"
    // and returns a new QuestItem object with the specified values
    public int compareTo(QuestItem q) {
        if(this.name.equals(q.name) && this.description.equals(q.description)){
            return 0;
        }
        return 1;
    }
}
