package app.main;

import app.data.CatService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int opc = -1;
        String[] buttons = {"1. See data", "2. See favourites", "3. Exit"};

        try {

            do {
                String options = (String) JOptionPane.showInputDialog(
                        null,
                        "Java Cats",
                        "Main menu",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        buttons,
                        buttons[0]
                );

                for (int i = 0; i < buttons.length; i++) {
                    if(options.equals(buttons[i])) {
                        opc = i;
                    }
                    System.out.println(i);
                }

                switch (opc) {

                    case 0:
                        CatService.getACat();
                    break;
                    case 1:
                        CatService.getFavourites();
                    break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }

            }while (opc!=1);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
