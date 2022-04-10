package com.example.lab4;

import android.os.Parcel;
import android.os.Parcelable;

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public SpacePhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public static SpacePhoto[] getSpacePhotos() {

        return new SpacePhoto[]{
                new SpacePhoto("https://i09.fotocdn.net/s129/4147396fdd722354/user_m/2919508186.jpg", "First"),
                new SpacePhoto("https://travelask.ru/system/images/files/001/045/482/wysiwyg/1.jpg?1520334618", "Second"),
                new SpacePhoto("https://realpolza.ru/wp-content/uploads/2020/05/reki-rossii-7-min-960x668.jpg", "Third"),
                new SpacePhoto("https://altaitop.ru/wp-content/uploads/2021/02/Krasnoyarskiy-kray-2-768x512.jpg", "Fourth"),
                new SpacePhoto("https://sib-prostor.ru/wp-content/uploads/2021/04/%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA-13-800x533.jpg", "Fifth"),
                new SpacePhoto("https://www.kebnanews.ir/images/docs/000411/n00411184-b.jpg", "Sixth"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
