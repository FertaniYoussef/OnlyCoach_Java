package com.hamza.services;

import com.hamza.entities.Feedback;
import com.hamza.utils.DatabaseConnection;
import com.hamza.utils.RelationObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {

    private static FeedbackService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public FeedbackService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static FeedbackService getInstance() {
        if (instance == null) {
            instance = new FeedbackService();
        }
        return instance;
    }

    public List<Feedback> getAll() {
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `feedback` AS x RIGHT JOIN `user` AS y ON x.user_id = y.id WHERE x.user_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listFeedback.add(new Feedback(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("user_id"), resultSet.getString("y.nom")),
                        resultSet.getString("sujet"),
                        resultSet.getString("email"),
                        resultSet.getString("description"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_feedback"))),
                        resultSet.getInt("status")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) feedback : " + exception.getMessage());
        }
        return listFeedback;
    }

    public List<RelationObject> getAllUsers() {
        List<RelationObject> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("nom")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }


    public boolean add(Feedback feedback) {

        String request = "INSERT INTO `feedback`(`user_id`, `sujet`, `email`, `description`, `date_feedback`, `status`) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, feedback.getUserId().getId());
            preparedStatement.setString(2, feedback.getSujet());
            preparedStatement.setString(3, feedback.getEmail());
            preparedStatement.setString(4, feedback.getDescription());
            preparedStatement.setDate(5, Date.valueOf(feedback.getDate()));
            preparedStatement.setInt(6, feedback.getStatus());

            preparedStatement.executeUpdate();
            System.out.println("Feedback added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) feedback : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Feedback feedback) {

        String request = "UPDATE `feedback` SET `user_id` = ?, `sujet` = ?, `email` = ?, `description` = ?, `date_feedback` = ?, `status` = ? WHERE `id`=" + feedback.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, feedback.getUserId().getId());
            preparedStatement.setString(2, feedback.getSujet());
            preparedStatement.setString(3, feedback.getEmail());
            preparedStatement.setString(4, feedback.getDescription());
            preparedStatement.setDate(5, Date.valueOf(feedback.getDate()));
            preparedStatement.setInt(6, feedback.getStatus());

            preparedStatement.executeUpdate();
            System.out.println("Feedback edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) feedback : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `feedback` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Feedback deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) feedback : " + exception.getMessage());
        }
        return false;
    }
}