package com.xmut.dao.data;

import com.xmut.dao.jdbc.ConnectionControl;
import com.xmut.hotel.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetData {
    private ResultSet resultSet;
    private ConnectionControl connectionControl;
    private String dataName;
    private List<Room> roomList;

    public GetData(){
        try {
            connectionControl = new ConnectionControl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Room> getRoom(String data){
        return null;
    }

}
