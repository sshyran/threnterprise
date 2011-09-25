<%-- 
    Document   : editProfilPage
    Created on : Sep 25, 2011, 12:28:57 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
    </head>
    <body>
        <h1>Edit Profile Page</h1>
        <table>
            <tr>
                <td>First Name</td>
                <td>: <input type="text" name="first_name" id="first_name" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>: <input type="text" name="last_name" id="last_name" /></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>: <input type="text" name="phone" id="phone" /></td>
            </tr>
            <tr>
                <td>Address</td>
                <td>: <input type="text" name="address" id="address" /></td>
            </tr>
            <tr>
                <td>Place of Birth</td>
                <td>: <input type="text" name="place_of_birth" id="place_of_birth" /></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td>: <input type="text" name="date_of_birth" id="date_of_birth" /></td>
            </tr>
            <tr>
                <td>New Password</td>
                <td>: <input type="password" name="password" id="password" /></td>
            </tr>
            <tr>
                <td>Confirm New Password</td>
                <td>: <input type="password" name="password_confirm" id="password_confirm" /></td>
            </tr>
            <tr>
                <td><input type="submit" name="submitProfil" id="submitProfil" /></td>
            </tr>
        </table>
    </body>
</html>
