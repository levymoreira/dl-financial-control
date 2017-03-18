(function() {
    'use strict';

    var jhiItemCount = {
        template: '<div class="info">' +
                    '<span data-translate="global.pagination.showing"></span> {{(($ctrl.page - 1) * $ctrl.itemsPerPage) == 0 ? 1 : (($ctrl.page - 1) * $ctrl.itemsPerPage + 1)}} - ' +
                    '{{($ctrl.page * $ctrl.itemsPerPage) < $ctrl.queryCount ? ($ctrl.page * $ctrl.itemsPerPage) : $ctrl.queryCount}} ' +
                    '<span data-translate="global.pagination.of"></span> {{$ctrl.queryCount}} <span data-translate="global.pagination.items"></span>.' +
                '</div>',
        bindings: {
            page: '<',
            queryCount: '<total',
            itemsPerPage: '<'
        }
    };

    angular
        .module('dlFinancialControlApp')
        .component('jhiItemCount', jhiItemCount);
})();
