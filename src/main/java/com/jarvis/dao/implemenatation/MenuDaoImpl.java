package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.MenuDao;
import com.jarvis.data.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

import static com.jarvis.etc.Constants.*;

/**
 * Created by Aniket on 01-03-2017.
 */
public class MenuDaoImpl extends BaseDao implements MenuDao {

    private static final Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);

    @Override
    public List<MenuItem> getMenuList(Integer restaurantId) {
        String sql = "select * from MenuItem where RestaurantId = '"+restaurantId+"'";
        logger.debug("Get Menu List for Restaurant ID : " + restaurantId);
        logger.debug("SQL before query"+sql);
        List<MenuItem> menuItemList= jdbcTemplateObject.query(sql,new MenuItemMapper());
        logger.debug("Menu List : " + menuItemList.toString());
        return menuItemList;
    }

    @Override
    public int addMenuItem(MenuItem item) {

        String sql = "insert into menuitem ( RestaurantId,ItemName,Price,Description,Category,SubCategory,IsVeg,PhotoPath) values (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,item.getRestaurantId());
                ps.setString(2,item.getItemName());
                ps.setFloat(3,item.getPrice());
                ps.setString(4,item.getDescription());
                ps.setString(5,item.getCategory());
                ps.setString(6,item.getSubCategory());
                ps.setBoolean(7,item.getIsVeg());
                ps.setString(8,item.getPhotoPath());
                return ps;
            }
        },keyHolder);
        return (int) keyHolder.getKey().longValue();
    }

    @Override
    public int deleteItem(Integer itemId) {
        String sql = "DELETE from menuitem WHERE "+MENU_ITEM_ID+ " = ?";
        logger.debug("Delete menu item with ID : " + itemId);
        logger.debug("SQL before query"+sql);
        return jdbcTemplateObject.update(sql,itemId);
    }

    @Override
    public int updateMenuItem(MenuItem menuItem) {

            StringBuilder builder = new StringBuilder();
            logger.debug("Update Menu Item : " + menuItem);
            List<Object> params = getUpdateQueryAndParams(menuItem,MENU_ITEM_TABLE_NAME,MENU_ITEM_ID,builder);
            String sql =builder.toString();
            return executeUpdateStatement(jdbcTemplateObject,sql,params);

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
            menuItem.setIsVeg(rs.getBoolean(MENU_ITEM_VEG_FLAG));
            menuItem.setPhotoPath(rs.getString(MENU_ITEM_PHOTO_PATH));
            return menuItem;
        }
    }
}

