var NativeDialog = {
    create: function( options, callback ) {

        options || (options = {});
        var scope = options.scope || null;

        var config = {
            title : options.title || '',
            items : options.items || ['Cancel'],
            destructiveButtonIndex : options.hasOwnProperty('destructiveButtonIndex') ? options.destructiveButtonIndex : undefined,
            cancelButtonIndex : options.hasOwnProperty('cancelButtonIndex') ? options.cancelButtonIndex : undefined
        };

        // will be the same for success & error
        var _callback = function(result) {
            var buttonValue = false, // value for cancelButton
                buttonIndex = result.buttonIndex;
            if(!config.cancelButtonIndex || buttonIndex != config.cancelButtonIndex) {
                buttonValue = config.items[buttonIndex];
            }
            if(typeof callback == 'function') callback.apply(scope, [buttonValue, buttonIndex]);
        };

        cordova.exec(
            _callback,
            _callback,
            "NativeDialog",
            "create",
            [ config ]
        );
    }
};

module.exports = NativeDialog;
