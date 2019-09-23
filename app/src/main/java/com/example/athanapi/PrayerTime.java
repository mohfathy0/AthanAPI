package com.example.athanapi;


public class PrayerTime {

    private data data;

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }
}

    class data{
        public timings timings;

        public timings getTimings() {
            return timings;
        }

        public void setTimings(timings timings) {
            this.timings = timings;


        }
    }


    class timings{
        private String Fajr,
                Sunrise,
                Dhuhr,
                Asr,
                Sunset,
                Maghrib,
                Isha,
                Imsak,
                Midnight;

        public timings(String fajr, String sunrise, String dhuhr, String asr, String sunset, String maghrib, String isha, String imsak, String midnight) {
            Fajr = fajr;
            Sunrise = sunrise;
            Dhuhr = dhuhr;
            Asr = asr;
            Sunset = sunset;
            Maghrib = maghrib;
            Isha = isha;
            Imsak = imsak;
            Midnight = midnight;
        }

        public String getFajr() {
            return Fajr;
        }

        public void setFajr(String fajr) {
            Fajr = fajr;
        }

        public String getSunrise() {
            return Sunrise;
        }

        public void setSunrise(String sunrise) {
            Sunrise = sunrise;
        }

        public String getDhuhr() {
            return Dhuhr;
        }

        public void setDhuhr(String dhuhr) {
            Dhuhr = dhuhr;
        }

        public String getAsr() {
            return Asr;
        }

        public void setAsr(String asr) {
            Asr = asr;
        }

        public String getSunset() {
            return Sunset;
        }

        public void setSunset(String sunset) {
            Sunset = sunset;
        }

        public String getMaghrib() {
            return Maghrib;
        }

        public void setMaghrib(String maghrib) {
            Maghrib = maghrib;
        }

        public String getIsha() {
            return Isha;
        }

        public void setIsha(String isha) {
            Isha = isha;
        }

        public String getImsak() {
            return Imsak;
        }

        public void setImsak(String imsak) {
            Imsak = imsak;
        }

        public String getMidnight() {
            return Midnight;
        }

        public void setMidnight(String midnight) {
            Midnight = midnight;
        }
    }


