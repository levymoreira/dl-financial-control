'use strict';

describe('Controller Tests', function() {

    describe('AccountName Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAccountName, MockUser, MockAccountType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAccountName = jasmine.createSpy('MockAccountName');
            MockUser = jasmine.createSpy('MockUser');
            MockAccountType = jasmine.createSpy('MockAccountType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'AccountName': MockAccountName,
                'User': MockUser,
                'AccountType': MockAccountType
            };
            createController = function() {
                $injector.get('$controller')("AccountNameDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dlFinancialControlApp:accountNameUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
