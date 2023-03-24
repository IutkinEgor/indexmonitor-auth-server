package org.indexmonitor.user.domain.enums;

public enum RecoveryQuestions {
    WHAT_IS_THE_NAME_OF_THE_FIRST_STREET_YOU_LIVED_ON ("What is the name of the first street you lived on?"),
    WHAT_IS_THE_NAME_OF_YOUR_FAVORITE_HISTORICAL_FIGURE	("What is the name of your favorite historical figure?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_SCHOOL_YOU_ATTENDED	("What is the name of the first school you attended?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_COMPANY_YOU_WORKED_FOR	("What is the name of the first company you worked for?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_BOOK_YOU_READ	("What is the name of the first book you read?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_CITY_YOU_VISITED	("What is the name of the first city you visited?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_MOVIE_YOU_SAW_IN_THE_THEATER	("What is the name of the first movie you saw in the theater?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_SONG_YOU_LEARNED_THE_LYRICS_TO	("What is the name of the first song you learned the lyrics to?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_THEME_PARK_YOU_VISITED	("What is the name of the first theme park you visited?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_PROFESSIONAL_SPORTS_TEAM_YOU_CHEERED_FOR	("What is the name of the first professional sports team you cheered for?"),
    WHAT_IS_THE_NAME_OF_YOUR_FAVORITE_TEACHER_IN_HIGH_SCHOOL	("What is the name of your favorite teacher in high school?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_MUSEUM_YOU_VISITED	("What is the name of the first museum you visited?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_FOREIGN_COUNTRY_YOU_VISITED	("What is the name of the first foreign country you visited?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_HISTORICAL_EVENT_YOU_LEARNED_ABOUT_IN_SCHOOL	("What is the name of the first historical event you learned about in school?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_THEME_PARK_RIDE_YOU_WENT_ON	("What is the name of the first theme park ride you went on?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_CONCERT_YOU_ATTENDED	("What is the name of the first concert you attended?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_COMPUTER_OR_VIDEO_GAME_YOU_PLAYED	("What is the name of the first computer or video game you played?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_SMARTPHONE_YOU_OWNED	("What is the name of the first smartphone you owned?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_WEBSITE_YOU_EVER_VISITED	("What is the name of the first website you ever visited?"),
    WHAT_IS_THE_NAME_OF_THE_FIRST_SOCIAL_MEDIA_PLATFORM_YOU_JOINED	("What is the name of the first social media platform you joined?");
    private final String question;

    RecoveryQuestions(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

}


