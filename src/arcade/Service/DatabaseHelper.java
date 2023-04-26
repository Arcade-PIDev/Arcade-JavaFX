/*
 * Copyright 2020-2021 LaynezCode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package arcade.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import arcade.Utils.database;
public class DatabaseHelper {


    public static int allExistingGamesthisDayByname(java.sql.Date date) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS cnt FROM jeux WHERE DATE(date) = ?";
            //"SELECT count(distinct user_id) as cnt FROM `blog` WHERE date = ?";

            PreparedStatement preparedStatement = database.getInstance().getCon().prepareStatement(sql);

            // Create a SimpleDateFormat object with the desired date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Convert the date to a string in the desired format
            String dateString = dateFormat.format(date);

             // Create a java.sql.Date object from the formatted string
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);

            // Set the prepared statement parameter using the java.sql.Date object
            preparedStatement.setDate(1, sqlDate);

            //preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (SQLException ex) {
            count = 0;
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }


    public static int allExistingGames(java.sql.Date date) {
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM `jeux`";
            PreparedStatement preparedStatement = database.getInstance().getCon().prepareStatement(sql);
           // preparedStetementProducts.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            count = 0;
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }





}
