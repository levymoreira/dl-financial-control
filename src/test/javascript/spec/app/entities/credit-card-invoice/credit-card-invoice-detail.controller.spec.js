'use strict';

describe('Controller Tests', function() {

    describe('CreditCardInvoice Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCreditCardInvoice, MockAccountName;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCreditCardInvoice = jasmine.createSpy('MockCreditCardInvoice');
            MockAccountName = jasmine.createSpy('MockAccountName');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CreditCardInvoice': MockCreditCardInvoice,
                'AccountName': MockAccountName
            };
            createController = function() {
                $injector.get('$controller')("CreditCardInvoiceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dlFinancialControlApp:creditCardInvoiceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
