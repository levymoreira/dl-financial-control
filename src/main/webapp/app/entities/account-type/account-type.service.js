(function() {
    'use strict';
    angular
        .module('dlFinancialControlApp')
        .factory('AccountType', AccountType);

    AccountType.$inject = ['$resource'];

    function AccountType ($resource) {
        var resourceUrl =  'api/account-types/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
