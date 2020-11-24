package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable{

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("coord")
        Coord CoordObject;
        @SerializedName("country")
        private String country;
        @SerializedName("population")
        private int population;
        @SerializedName("timezone")
        private int timezone;
        @SerializedName("sunrise")
        private int sunrise;
        @SerializedName("sunset")
        private int sunset;


        // Getter Methods

        public float getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Coord getCoord() {
            return CoordObject;
        }

        public String getCountry() {
            return country;
        }

        public float getPopulation() {
            return population;
        }

        public float getTimezone() {
            return timezone;
        }

        public float getSunrise() {
            return sunrise;
        }

        public float getSunset() {
            return sunset;
        }

        // Setter Methods

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCoord(Coord coordObject) {
            this.CoordObject = coordObject;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public void setTimezone(int timezone) {
            this.timezone = timezone;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }
}
