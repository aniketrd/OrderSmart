package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.MenuDao;
import com.jarvis.data.MenuItem;
import com.jarvis.data.RestaurantDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aniket on 01-03-2017.
 */
public class MenuDaoImpl extends BaseDao implements MenuDao {


    @Override
    public List<MenuItem> getMenuList(Integer restaurantId) {
        String sql = "select * from MenuItem where RestaurantId = '"+restaurantId+"'";
        List<MenuItem> menuItemList= jdbcTemplateObject.query(sql,new MenuItemMapper());
        return menuItemList;
    }

    @Override
    public int addMenuItem(MenuItem item) {

        String sql = "insert into menuitem ( RestaurantId,ItemName,Price,Description,Category,SubCategory,IsVeg,PhotoPath) values (?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] objs= new Object[]{item.getRestaurantId(),item.getItemName(),item.getPrice(),item.getDescription(),item.getCategory(),item.getSubCategory(),item.getVeg(),item.getPhotoPath()};
        return jdbcTemplateObject.update( sql, objs);

    }

    public class MenuItemMapper implements RowMapper<MenuItem> {

        @Override
        public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            MenuItem menuItem = new MenuItem();
            menuItem.setMenuItemId(rs.getInt("MenuItemId"));
            menuItem.setRestaurantId(rs.getInt("RestaurantId"));
            menuItem.setItemName(rs.getString("ItemName"));
            menuItem.setPrice(rs.getFloat("Price"));
            menuItem.setDescription(rs.getString("Description"));
            menuItem.setCategory(rs.getString("Category"));
            menuItem.setSubCategory(rs.getString("SubCategory"));
            menuItem.setVeg(rs.getBoolean("IsVeg"));
            menuItem.setPhotoPath(rs.getString("PhotoPath"));
            return menuItem;
        }
    }
}

