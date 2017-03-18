(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('account-type', {
            parent: 'entity',
            url: '/account-type?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.accountType.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/account-type/account-types.html',
                    controller: 'AccountTypeController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accountType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('account-type-detail', {
            parent: 'account-type',
            url: '/account-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.accountType.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/account-type/account-type-detail.html',
                    controller: 'AccountTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accountType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'AccountType', function($stateParams, AccountType) {
                    return AccountType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'account-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('account-type-detail.edit', {
            parent: 'account-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-type/account-type-dialog.html',
                    controller: 'AccountTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AccountType', function(AccountType) {
                            return AccountType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('account-type.new', {
            parent: 'account-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-type/account-type-dialog.html',
                    controller: 'AccountTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                description: null,
                                code: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('account-type', null, { reload: 'account-type' });
                }, function() {
                    $state.go('account-type');
                });
            }]
        })
        .state('account-type.edit', {
            parent: 'account-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-type/account-type-dialog.html',
                    controller: 'AccountTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AccountType', function(AccountType) {
                            return AccountType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('account-type', null, { reload: 'account-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('account-type.delete', {
            parent: 'account-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/account-type/account-type-delete-dialog.html',
                    controller: 'AccountTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AccountType', function(AccountType) {
                            return AccountType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('account-type', null, { reload: 'account-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
