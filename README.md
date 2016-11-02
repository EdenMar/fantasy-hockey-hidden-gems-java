This is a personal project that is currently a work in progress. It is meant to be a Java version of an earlier Python project, which can be found here: https://github.com/EdenMar/fantasy_hockey_gems

# Fantasy Hockey Gems Java

This is a Java application designed to help organize and view hockey stats so users can hopefully find some hidden gems for their fantasy hockey pool. I haven't had a chance work with Java and JUnit in a while, so I decided to make something that would give me an edge when looking for additions to my teams. 

# About

The application downloads the JSON data that NHL.com uses to display regular season stats for skaters and goalies. After that, it 
tracks individual players by creating a JSON database of their 10 most recent games played and total stats. If the application is run 
daily, every time the NHL JSON data is updated, the JSON database is updated, generating a game by game snapshot of a player's
performance. These differences are output as a series of reports in 3, 5, or 10 game spans. For example, the script is able to output the statistical leader in save percentage over the last 5 games, and lists in descending order the goalies' names. The script is best used everyday at a set time for consistent results. Because I'm not providing a database of NHL stats, the first run of the script will only provide the regular season stats. As the database grows, the reports will look more like game by game stats instead of the accumulated season stats.

# Usage

The application is currently a work in progress, so this is not yet ready for use anywhere else.

# Statistics 

* Goals
* Assists
* Points
* Penalty Minutes
* Powerplay Points
* Powerplay Goals
* Shots
* Save Percentage
* Wins
* Shots Against
* Saves
* Time on Ice (goalies)
* Goals Against Average

# Legal Stuff

I'm a big fan of the NHL, so I'd like to formally recognize that all NHL stats, logo, players, copyright, trademark etc etc are their own. I'm not trying to make any money off their name by selling this or anything, I'm just doing this for fun and programming practice. That said, this is also my own work, so I'm also licensing this under Apache License, version 2.0 "as is", without warranties or conditions.