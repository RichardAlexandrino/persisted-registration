package org.example.service;

import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void writeData(User user) {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
             BufferedWriter writer = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(writer)) {

            printWriter.println(user.getFormattedInformation());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    int id = Integer.parseInt(userData[0].trim());
                    String fullName = userData[1].trim();
                    int age = Integer.parseInt(userData[2].trim());
                    String profession = userData[3].trim();

                    User user = new User(fullName, age, profession);
                    user.setId(id);
                    userList.add(user);
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }

            if (!userList.isEmpty()) {
                System.out.println("\nUsers registered:");
                for (User user : userList) {
                    System.out.println(user.toString());
                }
            } else {
                System.out.println("No users registered yet.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid data format in the file.");
            e.printStackTrace();
        }
    }

    public boolean deleteUserById(int userIdToDelete) {
        List<User> userList = new ArrayList<>();
        boolean userDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    int id = Integer.parseInt(userData[0].trim());
                    String fullName = userData[1].trim();
                    int age = Integer.parseInt(userData[2].trim());
                    String profession = userData[3].trim();

                    User user = new User(fullName, age, profession);
                    user.setId(id);

                    if (user.getId() == userIdToDelete) {
                        userDeleted = true;
                    } else {
                        userList.add(user);
                    }
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userDeleted) {
            writeUsersToFile(userList);
        }

        return userDeleted;
    }

    private void writeUsersToFile(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter writer = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(writer)) {

            for (User user : userList) {
                printWriter.println(user.getFormattedInformation());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
