(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .factory('CreditCardInvoiceSearch', CreditCardInvoiceSearch);

    CreditCardInvoiceSearch.$inject = ['$resource'];

    function CreditCardInvoiceSearch($resource) {
        var resourceUrl =  'api/_search/credit-card-invoices/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
