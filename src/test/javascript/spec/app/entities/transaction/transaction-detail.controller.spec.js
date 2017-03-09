'use strict';

describe('Controller Tests', function() {

    describe('Transaction Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTransaction, MockUser, MockInstallmentGroup, MockCreditCardInvoice, MockCategory, MockClient, MockAccountName;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTransaction = jasmine.createSpy('MockTransaction');
            MockUser = jasmine.createSpy('MockUser');
            MockInstallmentGroup = jasmine.createSpy('MockInstallmentGroup');
            MockCreditCardInvoice = jasmine.createSpy('MockCreditCardInvoice');
            MockCategory = jasmine.createSpy('MockCategory');
            MockClient = jasmine.createSpy('MockClient');
            MockAccountName = jasmine.createSpy('MockAccountName');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Transaction': MockTransaction,
                'User': MockUser,
                'InstallmentGroup': MockInstallmentGroup,
                'CreditCardInvoice': MockCreditCardInvoice,
                'Category': MockCategory,
                'Client': MockClient,
                'AccountName': MockAccountName
            };
            createController = function() {
                $injector.get('$controller')("TransactionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dlFinancialControlApp:transactionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
