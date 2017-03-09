(function() {
    'use strict';
    angular
        .module('dlFinancialControlApp')
        .factory('CreditCardInvoice', CreditCardInvoice);

    CreditCardInvoice.$inject = ['$resource'];

    function CreditCardInvoice ($resource) {
        var resourceUrl =  'api/credit-card-invoices/:id';

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
