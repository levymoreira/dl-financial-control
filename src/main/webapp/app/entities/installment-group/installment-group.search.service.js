(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .factory('InstallmentGroupSearch', InstallmentGroupSearch);

    InstallmentGroupSearch.$inject = ['$resource'];

    function InstallmentGroupSearch($resource) {
        var resourceUrl =  'api/_search/installment-groups/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
