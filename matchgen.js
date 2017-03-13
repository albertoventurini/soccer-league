var teams = ["Tarantulas", "Juventus", "AC Milan", "Rebels", "Brescia", "Snakes", "FC Awesome", "Lions", "Atalanta", "grouches", "jfseijIUH8217 a aj uwa"];

for(var i = 0; i < 10000; i++) {
    var idx1 = Math.floor(Math.random() * teams.length);
    var idx2 = Math.floor(Math.random() * teams.length);
    var team1 = teams[idx1];
    var team2 = teams[idx2];
    var score1 = Math.floor(Math.random() * 10);
    var score2 = Math.floor(Math.random() * 10);

    console.log(team1 + " " + score1 + ", " + team2 + " " + score2);

}