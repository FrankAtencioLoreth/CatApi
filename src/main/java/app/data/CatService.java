package app.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CatService {

    public static void getACat() {
       try {
           OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search")
                   .get().build();
           Response response = client.newCall(request).execute();
           String jsonResponse = response.body().string();
           jsonResponse = jsonResponse.substring(1, jsonResponse.length());
           jsonResponse = jsonResponse.substring(0, jsonResponse.length()-1);

           Gson gs = new Gson();
           CatModel cat = gs.fromJson(jsonResponse, CatModel.class);

           URL url = new URL(cat.getUrl());
           Image image = ImageIO.read(url);
           ImageIcon icon = new ImageIcon(image);

           //Redimencionar imagen
           if(icon.getIconHeight() > 800 && icon.getIconWidth() > 800){
                Image img = icon.getImage();
                icon = new ImageIcon(img.getScaledInstance(800, 600, Image.SCALE_SMOOTH));
           }

           String menu = """
                  Options:
                  1. See other image.
                  2. Favorites.
                  3. Back to main menu.
                  """;

           int opc = -1;
           String[] buttons = {"1. See other image", "2. Favorites", "3. Back to main menu"};
           String idCat = cat.getId();
           String subMenu = (String) JOptionPane.showInputDialog(
                   null,
                   menu,
                   idCat,
                   JOptionPane.INFORMATION_MESSAGE,
                   icon,
                   buttons,
                   buttons[0]
           );

           for (int i = 0; i < buttons.length; i++) {
               if(subMenu.equals(buttons[i])) {
                   opc = i;
               }
           }

           switch (opc) {

               case 0:
                   getACat();
                   break;
               case 1:
                   favorites(cat);
                   break;
               default:
                   System.out.println("Invalid option");
                   break;
           }

       }catch (IOException e) {
           System.out.println("Exception in see cats: " + e);
       }
    }

    public static void favorites(CatModel cat) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, "{\"image_id\": \""+cat.getId()+"\"}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cat.API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            System.out.println("The cat {"+cat.getId()+"} has been add to favourites");
        }catch (Exception e) {
            System.out.println("Exception in favorites: " + e.getMessage());
        }
    }

    public static void getFavourites() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .get()
                    .addHeader("x-api-key", CatModel.API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String jsonResponse = response.body().string();

            Type listType = new TypeToken<List<CatFavResponse>>() {}.getType();
            Gson gson = new Gson();
            List<CatFavResponse> catResponse = gson.fromJson(jsonResponse, listType);

            for (CatFavResponse res : catResponse) {
                System.out.println(res);
            }

            if(!catResponse.isEmpty()){
                int min = 1;
                int max = catResponse.size();
                int random = (int) (Math.random() * (max - min + 1) + min);
                int index = random - 1;
                CatFavResponse catFavResponse = catResponse.get(index);
                URL url = new URL(catFavResponse.getImage().getUrl());
                Image image = ImageIO.read(url);
                ImageIcon icon = new ImageIcon(image);

                //Redimencionar imagen
                if(icon.getIconHeight() > 800 && icon.getIconWidth() > 800){
                    Image img = icon.getImage();
                    icon = new ImageIcon(img.getScaledInstance(800, 600, Image.SCALE_SMOOTH));
                }

                String menu = """
                  Options:
                  1. See other image.
                  2. Delete favorite.
                  3. Back to main menu.
                  """;

                int opc = -1;
                String[] buttons = {"1. See other image", "2. Delete favorite", "3. Back to main menu"};
                String idCat = catFavResponse.getId();
                String subMenu = (String) JOptionPane.showInputDialog(
                        null,
                        menu,
                        idCat,
                        JOptionPane.INFORMATION_MESSAGE,
                        icon,
                        buttons,
                        buttons[0]
                );

                for (int i = 0; i < buttons.length; i++) {
                    if(subMenu.equals(buttons[i])) {
                        opc = i;
                    }
                }

                switch (opc) {

                    case 0:
                        getFavourites();
                        break;
                    case 1:
                        deleteFavourites(catFavResponse);
                    break;
                    default:
                        System.out.println("Invalid option");
                    break;
                }

            }

        }catch (Exception e) {
            System.out.println("Error on get favourites: " + e.getMessage());
        }
    }


    public static void deleteFavourites(CatFavResponse cat){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+cat.getId())
                    .delete()
                    .addHeader("x-api-key", CatModel.API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String jsonResponse = response.body().string();

            JOptionPane.showMessageDialog(
                    null, "The cat "+cat.getId()+" has been deleted"
            );

        }catch (Exception e) {
            System.out.println("Error to delete: " + e.getMessage());
        }
    }

}
