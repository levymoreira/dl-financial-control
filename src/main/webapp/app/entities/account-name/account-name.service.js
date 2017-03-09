(function() {
    'use strict';
    angular
        .module('dlFinancialControlApp')
        .factory('AccountName', AccountName);

    AccountName.$inject = ['$resource'];

    function AccountName ($resource) {
        var resourceUrl =  'api/account-names/:id';

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
