package com.hamza.services;

import com.hamza.entities.Sponsor;
import com.hamza.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SponsorService {

    private static SponsorService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public SponsorService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static SponsorService getInstance() {
        if (instance == null) {
            instance = new SponsorService();
        }
        return instance;
    }

    public List<Sponsor> getAll() {
        List<Sponsor> listSponsor = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `sponsor`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listSponsor.add(new Sponsor(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date"))),
                        resultSet.getString("description"),
                        resultSet.getString("image")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) sponsor : " + exception.getMessage());
        }
        return listSponsor;
    }


    public boolean add(Sponsor sponsor) {

        String request = "INSERT INTO `sponsor`(`nom`, `date`, `description`, `image`) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, sponsor.getNom());
            preparedStatement.setDate(2, Date.valueOf(sponsor.getDate()));
            preparedStatement.setString(3, sponsor.getDescription());
            preparedStatement.setString(4, sponsor.getImage());

            preparedStatement.executeUpdate();
            System.out.println("Sponsor added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) sponsor : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Sponsor sponsor) {

        String request = "UPDATE `sponsor` SET `nom` = ?, `date` = ?, `description` = ?, `image` = ? WHERE `id`=" + sponsor.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, sponsor.getNom());
            preparedStatement.setDate(2, Date.valueOf(sponsor.getDate()));
            preparedStatement.setString(3, sponsor.getDescription());
            preparedStatement.setString(4, sponsor.getImage());

            preparedStatement.executeUpdate();
            System.out.println("Sponsor edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) sponsor : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `sponsor` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Sponsor deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) sponsor : " + exception.getMessage());
        }
        return false;
    }
}
