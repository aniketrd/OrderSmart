package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.MenuDao;
import com.jarvis.data.MenuItem;
import com.jarvis.data.RestaurantDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.jarvis.etc.Constants.*;

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
            menuItem.setMenuItemId(rs.getInt(MENU_ITEM_ID));
            menuItem.setRestaurantId(rs.getInt(RST_ID));
            menuItem.setItemName(rs.getString(MENU_ITEM_NAME));
            menuItem.setPrice(rs.getFloat(MENU_ITEM_PRICE));
            menuItem.setDescription(rs.getString(MENU_ITEM_DESC));
            menuItem.setCategory(rs.getString(MENU_ITEM_CATEGORY));
            menuItem.setSubCategory(rs.getString(MENU_ITEM_SUB_CAT));
            menuItem.setVeg(rs.getBoolean(MENU_ITEM_VEG_FLAG));
            menuItem.setPhotoPath(rs.getString(MENU_ITEM_PHOTO_PATH));
            return menuItem;
        }
    }
}

