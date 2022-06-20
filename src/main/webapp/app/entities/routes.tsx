import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Team from './team';
import Landscape from './landscape';
import LandscapeElement from './landscape-element';
import Project from './project';
import ProjectElement from './project-element';
import ContentStoreInternal from './content-store-internal';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}team`} component={Team} />
        <ErrorBoundaryRoute path={`${match.url}landscape`} component={Landscape} />
        <ErrorBoundaryRoute path={`${match.url}landscape-element`} component={LandscapeElement} />
        <ErrorBoundaryRoute path={`${match.url}project`} component={Project} />
        <ErrorBoundaryRoute path={`${match.url}project-element`} component={ProjectElement} />
        <ErrorBoundaryRoute path={`${match.url}content-store-internal`} component={ContentStoreInternal} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
