package iptvnew;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import helper.PortalServices;
import helper.UrlApp;
import meklib.MCrypt;

public class IpTvNewData {

    private Context context;
    private PortalServices portalServices;

    private ArrayList<IpTvNewData> arrDataTvMain = new ArrayList<IpTvNewData>();
    private ArrayList<IpTvNewData> arrDataTvCat = new ArrayList<IpTvNewData>();
    private ArrayList<IpTvNewData> arrDataTvList = new ArrayList<IpTvNewData>();

    private String tv_id;
    private String tv_name;
    private String tv_img;
    private String tv_color;
    private String tv_link;
    private MCrypt mcrypt = new MCrypt();

    public IpTvNewData(Context context) {
        super();
        this.context = context;
        portalServices = new PortalServices();
    }

    public IpTvNewData(String tv_id, String tv_name, String tv_img) {
        this.tv_id = tv_id;
        this.tv_name = tv_name;
        this.tv_img = tv_img;
    }

    public IpTvNewData(String tv_id, String tv_name, String tv_img, String tv_color) {
        this.tv_id = tv_id;
        this.tv_name = tv_name;
        this.tv_img = tv_img;
        this.tv_color = tv_color;
    }

    public IpTvNewData(String tv_id, String tv_name, String tv_img, String tv_color, String tv_link) {
        this.tv_id = tv_id;
        this.tv_name = tv_name;
        this.tv_img = tv_img;
        this.tv_color = tv_color;
        this.tv_link = tv_link;
    }

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_img() {
        return tv_img;
    }

    public void setTv_img(String tv_img) {
        this.tv_img = tv_img;
    }

    public String getTv_color() {
        return tv_color;
    }

    public void setTv_color(String tv_color) {
        this.tv_color = tv_color;
    }

    public String getTv_link() {
        return tv_link;
    }

    public void setTv_link(String tv_link) {
        this.tv_link = tv_link;
    }

    public ArrayList<IpTvNewData> getMain() {
        String resultData = portalServices.makePortalCall(null, UrlApp.MAIN_TV, PortalServices.GET);
        try {
            String decrypted = new String(mcrypt.decrypt(resultData));
            JSONObject jsonObject = new JSONObject(decrypted);
            JSONArray jArrData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArrData.length(); i++) {
                JSONObject jData = jArrData.getJSONObject(i);
                tv_id = jData.getString("id");
                tv_name = jData.getString("name");
                tv_img = jData.getString("img");
                tv_color = jData.getString("colors");

                arrDataTvMain.add(new IpTvNewData(tv_id, tv_name, tv_img, tv_color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDataTvMain;

    }

    public ArrayList<IpTvNewData> getCategory(String main_id) {
        String resultData = portalServices.makePortalCall(null, UrlApp.CATEGORY_TV + "?main_id=" + main_id, PortalServices.GET);
        try {
            String decrypted = new String(mcrypt.decrypt(resultData));
            JSONObject jsonObject = new JSONObject(decrypted);
            JSONArray jArrData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArrData.length(); i++) {
                JSONObject jData = jArrData.getJSONObject(i);
                tv_id = jData.getString("id");
                tv_name = jData.getString("name");
                tv_img = jData.getString("imgs");
                tv_color = jData.getString("colors");

                arrDataTvCat.add(new IpTvNewData(tv_id, tv_name, tv_img, tv_color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDataTvCat;

    }

    public ArrayList<IpTvNewData> getCategoryNewTV(String main_id) {
        String resultData = portalServices.makePortalCall(null, UrlApp.CATEGORY_TV_NEW + "?main_id=" + main_id, PortalServices.GET);
        try {
            String decrypted = new String(mcrypt.decrypt(resultData));
            JSONObject jsonObject = new JSONObject(decrypted);
            JSONArray jArrData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArrData.length(); i++) {
                JSONObject jData = jArrData.getJSONObject(i);
                tv_id = jData.getString("id");
                tv_name = jData.getString("name");
                tv_img = jData.getString("imgs");
                tv_color = jData.getString("colors");

                arrDataTvCat.add(new IpTvNewData(tv_id, tv_name, tv_img, tv_color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDataTvCat;

    }


    public ArrayList<IpTvNewData> getList(String id) {
        String resultData = portalServices.makePortalCall(null, UrlApp.CATEGORY_TV + "?id=" + id, PortalServices.GET);
        try {
            String decrypted = new String(mcrypt.decrypt(resultData));
            JSONObject jsonObject = new JSONObject(decrypted);
            JSONArray jArrData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArrData.length(); i++) {
                JSONObject jData = jArrData.getJSONObject(i);
                tv_id = jData.getString("id");

                Log.d("12novV1", "id_tv at IpTvData ==> " + tv_id);

                tv_name = jData.getString("name");
                tv_img = jData.getString("logo");
                tv_color = "";
                tv_link = jData.getString("link");

                Log.d("12novV1", "tv_link ==> " + tv_link);

                arrDataTvList.add(new IpTvNewData(tv_id, tv_name, tv_img, tv_color, tv_link));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDataTvList;

    }

    public ArrayList<IpTvNewData> getListNewTV(String id) {
        String resultData = portalServices.makePortalCall(null, UrlApp.CATEGORY_TV_NEW + "?id=" + id, PortalServices.GET);
        try {
            String decrypted = new String(mcrypt.decrypt(resultData));
            JSONObject jsonObject = new JSONObject(decrypted);
            JSONArray jArrData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArrData.length(); i++) {
                JSONObject jData = jArrData.getJSONObject(i);
                tv_id = jData.getString("id");

                Log.d("12novV1", "id_tv at IpTvData ==> " + tv_id);

                tv_name = jData.getString("name");
                tv_img = jData.getString("logo");
                tv_color = "";
                tv_link = jData.getString("link");

                Log.d("12novV1", "tv_link ==> " + tv_link);

                arrDataTvList.add(new IpTvNewData(tv_id, tv_name, tv_img, tv_color, tv_link));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDataTvList;

    }


}
