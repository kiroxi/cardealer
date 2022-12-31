function getModels(makeId) {
    let modelSelect = $('#modelName');
    let carInfoBlock = $('#carInfo');

    modelSelect.empty();

    if (makeId == -1) {
        carInfoBlock.hide();
    } else {
        carInfoBlock.show();
        $.get("/cars/models", { make : makeId })
            .done(function(data) {
                for( let i = 0; i < data.length; i++ ) {
                    let model = data[i];
                    modelSelect.append('<option value="' + model.id + '">' + model.name + '</option>');
                }
            });
    }
}