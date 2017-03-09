(function() {
    'use strict';
    angular
        .module('dlFinancialControlApp')
        .factory('InstallmentGroup', InstallmentGroup);

    InstallmentGroup.$inject = ['$resource', 'DateUtils'];

    function InstallmentGroup ($resource, DateUtils) {
        var resourceUrl =  'api/installment-groups/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertDateTimeFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
