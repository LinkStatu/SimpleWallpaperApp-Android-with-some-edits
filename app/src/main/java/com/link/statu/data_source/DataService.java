package com.link.statu.data_source;

import com.link.statu.models.CategoryPOJO;
import com.link.statu.models.WallsPOJO;
import com.link.statu.utils.SQLFav;

import java.util.ArrayList;

public abstract class DataService {

    public static final int PER_PAGE_ITEM = 16;

    protected DataService(SQLCategories sqlCategories, SQLFav sqlFav) {
        this.sqlCategories = sqlCategories;
        this.sqlFav = sqlFav;
    }

    public enum QueryType {
        NONE,
        CATEGORY,
        FAVORITE,
        SEARCH
    }


    protected final SQLCategories sqlCategories;
    protected final SQLFav sqlFav;

    public ArrayList<CategoryPOJO> getCategories(String query) {
        return sqlCategories.getCategories(query);
    }

    abstract public void setupCategories(OnCategoriesLoaded onCategoriesLoaded);

    abstract public void getWallpapers(int page, DataService.QueryType type, String string, OnWallpapersLoaded onWallpapersLoaded);


    public void toggleFavorite(WallsPOJO wallsPojo, boolean favorite) {
        sqlFav.toggleFavorite(wallsPojo, favorite);
    }

    public boolean isFavorite(String url) {
        return sqlFav.isFavorite(url);
    }

    public interface OnCategoriesLoaded {
        void onCategoriesLoaded();
    }
    public interface OnWallpapersLoaded {
        void onWallpapersLoaded(ArrayList<WallsPOJO> list);
    }

}
