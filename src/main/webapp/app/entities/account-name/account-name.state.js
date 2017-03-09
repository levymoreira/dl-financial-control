(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('account-name', {
            parent: 'entity',
            url: '/account-name',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.accountName.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/account-name/account-names.html',
                    controller: 'AccountNameController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accountName');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('account-name-detail', {
            parent: 'account-name',
            url: '/account-name/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.accountName.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/account-name/account-name-detail.html',
                    controller: 'AccountNameDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accountName');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'AccountName', function($stateParams, AccountName) {
                    return AccountName.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'account-name',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('account-name-detail.edit', {
            parent: 'account-name-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-name/account-name-dialog.html',
                    controller: 'AccountNameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AccountName', function(AccountName) {
                            return AccountName.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('account-name.new', {
            parent: 'account-name',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-name/account-name-dialog.html',
                    controller: 'AccountNameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('account-name', null, { reload: 'account-name' });
                }, function() {
                    $state.go('account-name');
                });
            }]
        })
        .state('account-name.edit', {
            parent: 'account-name',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-name/account-name-dialog.html',
                    controller: 'AccountNameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AccountName', function(AccountName) {
                            return AccountName.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('account-name', null, { reload: 'account-name' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('account-name.delete', {
            parent: 'account-name',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-name/account-name-delete-dialog.html',
                    controller: 'AccountNameDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AccountName', function(AccountName) {
                            return AccountName.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('account-name', null, { reload: 'account-name' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
