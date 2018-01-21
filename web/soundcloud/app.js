function playSomeSound(genre) {
    SC.get('/tracks', {
        genres: genre,
        bpm: {
            from: 100
        }
    }, function(tracks) {
        var random = Math.floor(Math.random() * 49);
        SC.oEmbed(tracks[random].uri, { auto_play: true }, document.getElementById('target'));
    });
}

window.onload = function() {
    SC.initialize({
        client_id: 'c4845f147414a8bc98f620b913012879'
    });

    var menuLinks = document.getElementsByClassName('genre');
    var menuLink;
    for (var i = 0; i < menuLinks.length; i++) {
        menuLink = menuLinks[i];
        menuLink.onclick = function(e) {
            e.preventDefault();
            playSomeSound(menuLink.innerHTML);
        };
    }
};
