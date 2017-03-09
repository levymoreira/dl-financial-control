(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .factory('AccountNameSearch', AccountNameSearch);

    AccountNameSearch.$inject = ['$resource'];

    function AccountNameSearch($resource) {
        var resourceUrl =  'api/_search/account-names/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
