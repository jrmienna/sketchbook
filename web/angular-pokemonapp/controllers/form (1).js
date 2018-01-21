function FormCtrl($scope) {
    $scope.fields = [
        {type: "text", label: "Name"},
        {type: "text", label: "Type"}
    ];

    $scope.pokemons = [
        {name: "Name1", type: "Type"},
        {name: "Name2", type: "Type"},
        {name: "Name3", type: "Type"},
        {name: "Name4", type: "Type"},
    ];

    $scope.createPokemon = function() {
        $scope.pokemons.push({
            name: "New",
            type: "TypeO"
        });

    };
}
