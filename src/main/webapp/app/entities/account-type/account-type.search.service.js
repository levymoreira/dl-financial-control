(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .factory('AccountTypeSearch', AccountTypeSearch);

    AccountTypeSearch.$inject = ['$resource'];

    function AccountTypeSearch($resource) {
        var resourceUrl =  'api/_search/account-types/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
